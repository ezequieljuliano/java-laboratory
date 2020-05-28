package br.com.ezequieljuliano.bookmark.domain.repository;

import br.com.ezequieljuliano.bookmark.domain.model.Permissao;
import br.com.ezequieljuliano.bookmark.infrastructure.support.repository.BaseJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissaoRepository extends BaseJpaRepository<Permissao> {

}
