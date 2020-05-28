package br.com.ezequieljuliano.bookmark.domain.repository;

import br.com.ezequieljuliano.bookmark.domain.model.Usuario;
import br.com.ezequieljuliano.bookmark.infrastructure.support.repository.BaseJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends BaseJpaRepository<Usuario> {

    Optional<Usuario> findByUsername(String username);

}
