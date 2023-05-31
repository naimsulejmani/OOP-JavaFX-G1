package com.example.oopjavafxg1.repositories;

import com.example.oopjavafxg1.infrastructures.CrudRepository;
import com.example.oopjavafxg1.infrastructures.DbHelper;
import com.example.oopjavafxg1.models.Person;

import java.sql.*;
import java.util.List;

public class PersonRepository implements CrudRepository<Person, Integer> {
    @Override
    public boolean add(Person entity) {
        String query = """
                INSERT INTO dbo.Person(Name, Surname, Comment, Birthdate) VALUES (?,?,?,?);
                """;
        try (
                Connection connection = DbHelper.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ) {
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
        return false;
    }

    @Override
    public boolean remove(Integer id) {
        return false;
    }

    @Override
    public List<Person> findAll() {
        return null;
    }

    @Override
    public Person findById(Integer id) {
        return null;
    }

    @Override
    public Person toObject(ResultSet resultSet) {
        return null;
    }
}
