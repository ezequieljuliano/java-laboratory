package br.com.ezequieljuliano.bookmark.infrastructure.jpa.security.repository;

import br.com.ezequieljuliano.bookmark.domain.entity.security.Usuario;
import br.com.ezequieljuliano.bookmark.infrastructure.jpa.CrudRepositoryJpa;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends CrudRepositoryJpa<Usuario> {

    Optional<Usuario> findByUsername(String username);

}
