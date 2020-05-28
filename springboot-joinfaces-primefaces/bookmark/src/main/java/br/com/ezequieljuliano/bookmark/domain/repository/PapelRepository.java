package br.com.ezequieljuliano.bookmark.domain.repository;

import br.com.ezequieljuliano.bookmark.domain.model.Papel;
import br.com.ezequieljuliano.bookmark.infrastructure.support.repository.BaseJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PapelRepository extends BaseJpaRepository<Papel> {

    List<Papel> findByNomeContainingIgnoreCase(String nome);

}
