package org.example.repository;

import java.util.List;

public interface CrudRepository<T> {
    default void save(T entity) {
        throw new UnsupportedOperationException("Not supported");
    }

    default void update(T entity) {
        throw new UnsupportedOperationException("Not supported");
    }

    default void delete(Long id) {
        throw new UnsupportedOperationException("Not supported");
    }

    default T findById(Long id) {
        throw new UnsupportedOperationException("Not supported");
    }

    default List<T> findAll() {
        throw new UnsupportedOperationException("Not supported");
    }


}
