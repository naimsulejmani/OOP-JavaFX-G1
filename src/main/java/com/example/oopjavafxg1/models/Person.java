package com.example.oopjavafxg1.models;

import java.time.LocalDate;

public class Person {
    private int id;
    private String name;
    private String surname;
    private String comment;
    private LocalDate birthdate;

    public Person() {
    }

    public Person(int id, String name, String surname, String comment, LocalDate birthdate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.comment = comment;
        this.birthdate = birthdate;
    }

    public Person(String name, String surname, String comment, LocalDate birthdate) {
        this(0, name, surname, comment, birthdate);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }
}









