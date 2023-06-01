package com.example.oopjavafxg1.repositories;

import com.example.oopjavafxg1.infrastructures.CrudRepository;
import com.example.oopjavafxg1.infrastructures.DbHelper;
import com.example.oopjavafxg1.models.Person;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersonRepository implements CrudRepository<Person, Integer> {
    @Override
    public boolean add(Person entity) {
        String query = """
                INSERT INTO dbo.Person(Name, Surname, Comment, Birthdate) VALUES (?,?,?,?);
                """;
        try (Connection connection = DbHelper.getConnection(); PreparedStatement statement = connection.prepareStatement(query);) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getSurname());
            statement.setString(3, entity.getComment());

            statement.setDate(4, Date.valueOf(entity.getBirthdate()));

            return statement.executeUpdate() >= 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean modify(Integer id, Person entity) {
        String query = """
                UPDATE dbo.Person
                SET Name = ?,
                    Surname = ?,
                    Comment = ?,
                    Birthdate = ?
                WHERE Id = ?
                """;
        try (Connection connection = DbHelper.getConnection(); PreparedStatement statement = connection.prepareStatement(query);) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getSurname());
            statement.setString(3, entity.getComment());
            statement.setDate(4, Date.valueOf(entity.getBirthdate()));
            statement.setInt(5, id);

            return statement.executeUpdate() >= 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public boolean remove(Integer id) {
        String query = "DELETE FROM dbo.Person WHERE Id = ?";
        try (Connection connection = DbHelper.getConnection(); PreparedStatement statement = connection.prepareStatement(query);) {
            statement.setInt(1, id);

            return statement.executeUpdate() >= 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Person> findAll() {
        String query = "SELECT * FROM dbo.Person";
        try (Connection connection = DbHelper.getConnection(); PreparedStatement statement = connection.prepareStatement(query);) {
            List<Person> persons = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
//                Person person = toObject(resultSet);
                persons.add(toObject(resultSet));
            }
            return persons;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Person findById(Integer id) {
        String query = "SELECT * FROM dbo.Person WHERE Id = ?";
        try (Connection connection = DbHelper.getConnection(); PreparedStatement statement = connection.prepareStatement(query);) {
            statement.setInt(1, id);
            Person person = null;
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                person = toObject(resultSet);
            }
            return person;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Person toObject(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("Id");
        String name = resultSet.getString("Name");
        String surname = resultSet.getString("Surname");
        String comment = null;
        if (resultSet.getObject("Comment") != null) {
            comment = resultSet.getString("Comment");
        }
        LocalDate birthdate = resultSet.getDate("Birthdate").toLocalDate();
        return new Person(id, name, surname, comment, birthdate);
    }
}
