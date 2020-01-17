package com.serheev.app.dao;

import java.util.List;
import java.util.Optional;

public interface DaoInterface<T> {
    Optional<T> get(long id);
    List<T> getAll();
    T save(T t);
    void update(T t, String[] params);
    void delete(long id);
}
