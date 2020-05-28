package br.com.ezequieljuliano.bookmark.domain.repository;

import br.com.ezequieljuliano.bookmark.domain.model.Categoria;
import br.com.ezequieljuliano.bookmark.infrastructure.support.repository.BaseJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends BaseJpaRepository<Categoria> {

    List<Categoria> findByNomeContainingIgnoreCase(String nome);

}
