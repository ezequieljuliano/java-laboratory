package br.com.ezequieljuliano.bookmark.infrastructure.jpa.security;

import br.com.ezequieljuliano.bookmark.domain.entity.security.Usuario;
import br.com.ezequieljuliano.bookmark.domain.gateway.security.UsuarioGateway;
import br.com.ezequieljuliano.bookmark.infrastructure.jpa.CrudGatewayJpa;
import br.com.ezequieljuliano.bookmark.infrastructure.jpa.security.repository.UsuarioRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsuarioGatewayJpa
        extends CrudGatewayJpa<Usuario, UsuarioRepository>
        implements UsuarioGateway {

    @Override
    public Optional<Usuario> findByUsername(String username) {
        return getRepository().findByUsername(username);
    }
}
