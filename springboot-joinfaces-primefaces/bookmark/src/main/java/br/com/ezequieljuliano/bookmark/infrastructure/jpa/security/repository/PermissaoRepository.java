package br.com.ezequieljuliano.bookmark.infrastructure.jpa.security.repository;

import br.com.ezequieljuliano.bookmark.domain.entity.security.Permissao;
import br.com.ezequieljuliano.bookmark.infrastructure.jpa.CrudRepositoryJpa;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissaoRepository extends CrudRepositoryJpa<Permissao> {

}
