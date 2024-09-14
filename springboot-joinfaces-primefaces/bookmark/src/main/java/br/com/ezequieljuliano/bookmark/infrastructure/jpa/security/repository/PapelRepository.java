package br.com.ezequieljuliano.bookmark.infrastructure.jpa.security.repository;

import br.com.ezequieljuliano.bookmark.domain.entity.security.Papel;
import br.com.ezequieljuliano.bookmark.infrastructure.jpa.CrudRepositoryJpa;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PapelRepository extends CrudRepositoryJpa<Papel> {

    List<Papel> findByNomeContainingIgnoreCase(String nome);

}
