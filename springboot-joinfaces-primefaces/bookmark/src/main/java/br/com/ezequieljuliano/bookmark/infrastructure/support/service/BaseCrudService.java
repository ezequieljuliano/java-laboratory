package br.com.ezequieljuliano.bookmark.infrastructure.support.service;

import br.com.ezequieljuliano.bookmark.infrastructure.support.repository.BaseJpaRepository;
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

public abstract class BaseCrudService<Entity, Repository extends BaseJpaRepository<Entity>> implements CrudService<Entity> {

    @Autowired
    @Getter(AccessLevel.PROTECTED)
    private Repository repository;

    @Override
    public List<Entity> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Entity> findAll(Sort sort){
        return repository.findAll(sort);
    }

    @Override
    public Page<Entity> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<Entity> findAll(Specification<Entity> specification, Pageable pageable) {
        return repository.findAll(specification, pageable);
    }

    @Override
    public Optional<Entity> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Entity findOne(UUID id) {
        return repository.getOne(id);
    }

    @Override
    public Entity save(Entity entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Entity entity) {
        repository.delete(entity);
    }

}
