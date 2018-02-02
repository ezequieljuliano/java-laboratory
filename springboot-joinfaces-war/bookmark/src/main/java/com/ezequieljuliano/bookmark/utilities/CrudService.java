package com.ezequieljuliano.bookmark.utilities;

import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class CrudService<T, R extends JpaRepository<T, Long>> {

    @Autowired
    @Getter(AccessLevel.PROTECTED)
    private R repository;

    public List<T> findAll() {
        return repository.findAll();
    }

    public T getOne(Long id) {
        return repository.getOne(id);
    }

    protected void beforeSave(T entity) {
    }

    protected void afterSave(T entity) {
    }

    public void save(T entity) {
        beforeSave(entity);
        repository.save(entity);
        afterSave(entity);
    }

    protected void beforeDelete(T entity) {
    }

    protected void afterDelete(T entity) {
    }

    public void delete(T entity) {
        beforeDelete(entity);
        repository.delete(entity);
        afterDelete(entity);
    }
}
