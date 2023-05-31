package com.example.oopjavafxg1.infrastructures;

import java.sql.ResultSet;

@FunctionalInterface
public interface ToObjectAble<T> {
    T toObject(ResultSet resultSet);
}
