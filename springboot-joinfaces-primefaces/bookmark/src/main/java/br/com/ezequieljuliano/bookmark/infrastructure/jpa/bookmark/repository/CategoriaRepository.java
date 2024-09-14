package br.com.ezequieljuliano.bookmark.infrastructure.jpa.bookmark.repository;

import br.com.ezequieljuliano.bookmark.domain.entity.bookmark.Categoria;
import br.com.ezequieljuliano.bookmark.infrastructure.jpa.CrudRepositoryJpa;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends CrudRepositoryJpa<Categoria> {

    List<Categoria> findByNomeContainingIgnoreCase(String nome);

}
