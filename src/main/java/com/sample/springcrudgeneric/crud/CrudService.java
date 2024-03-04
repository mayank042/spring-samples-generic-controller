package com.sample.springcrudgeneric.crud;

import java.util.Optional;

public interface CrudService<T, ID> {

    Iterable<T> getAll();
    Optional<T> getById(ID id);
    boolean existsById(ID id);
    T create(T entity);
    T update(ID id, T entity);
    void delete(ID id);
}
