package br.com.ezequieljuliano.bookmark.domain.service;

import br.com.ezequieljuliano.bookmark.domain.gateway.CrudGateway;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class BaseService<Entity, Repository extends CrudGateway<Entity>>
        implements CrudService<Entity> {

    @Autowired
    @Getter(AccessLevel.PROTECTED)
    private Repository gateway;

    @Override
    public List<Entity> findAll() {
        return gateway.findAll();
    }

    @Override
    public List<Entity> findAll(Sort sort) {
        return gateway.findAll(sort);
    }

    @Override
    public Page<Entity> findAll(Pageable pageable) {
        return gateway.findAll(pageable);
    }

    @Override
    public Page<Entity> findAll(Specification<Entity> specification, Pageable pageable) {
        return gateway.findAll(specification, pageable);
    }

    @Override
    public Optional<Entity> findById(UUID id) {
        return gateway.findById(id);
    }

    @Override
    public Entity findOne(UUID id) {
        return gateway.findOne(id);
    }

    @Override
    public Entity save(Entity entity) {
        return gateway.save(entity);
    }

    @Override
    public void delete(Entity entity) {
        gateway.delete(entity);
    }

}
