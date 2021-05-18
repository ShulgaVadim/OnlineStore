package com.vadim.dao;

import java.util.List;

public interface Dao<T> {

    void add(T t);

    List<T> getAll();

    T getById(Long id);

    void update(T t);

    void remove(T t);
}
