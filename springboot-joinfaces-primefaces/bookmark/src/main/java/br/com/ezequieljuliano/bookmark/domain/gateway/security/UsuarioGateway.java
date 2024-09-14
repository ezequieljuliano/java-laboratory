package br.com.ezequieljuliano.bookmark.domain.gateway.security;

import br.com.ezequieljuliano.bookmark.domain.entity.security.Usuario;
import br.com.ezequieljuliano.bookmark.domain.gateway.CrudGateway;

import java.util.Optional;

public interface UsuarioGateway extends CrudGateway<Usuario> {

    Optional<Usuario> findByUsername(String username);

}
