package com.example.oopjavafxg1.infrastructures;

import java.util.List;

public interface CrudRepository<T, Tid> extends ToObjectAble<T> {
    boolean add(T entity);

    boolean modify(Tid id, T entity);

    boolean remove(Tid id);

    List<T> findAll();

    T findById(Tid id);
}
