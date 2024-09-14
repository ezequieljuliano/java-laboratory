package br.com.ezequieljuliano.bookmark.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface CrudRepositoryJpa<Entity>
        extends JpaRepository<Entity, UUID>, JpaSpecificationExecutor<Entity> {

}
