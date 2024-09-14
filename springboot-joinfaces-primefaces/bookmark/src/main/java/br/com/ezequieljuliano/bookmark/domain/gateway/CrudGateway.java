package br.com.ezequieljuliano.bookmark.domain.gateway;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CrudGateway<Entity> {

    List<Entity> findAll();

    List<Entity> findAll(Sort sort);

    Page<Entity> findAll(Pageable pageable);

    Page<Entity> findAll(Specification<Entity> specification, Pageable pageable);

    Entity findOne(UUID id);

    Optional<Entity> findById(UUID id);

    Entity save(Entity entity);

    void delete(Entity entity);

}
