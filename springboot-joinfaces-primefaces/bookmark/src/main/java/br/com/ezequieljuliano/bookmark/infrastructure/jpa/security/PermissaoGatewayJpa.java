package br.com.ezequieljuliano.bookmark.infrastructure.jpa.security;

import br.com.ezequieljuliano.bookmark.domain.entity.security.Permissao;
import br.com.ezequieljuliano.bookmark.domain.gateway.security.PermissaoGateway;
import br.com.ezequieljuliano.bookmark.infrastructure.jpa.CrudGatewayJpa;
import br.com.ezequieljuliano.bookmark.infrastructure.jpa.security.repository.PermissaoRepository;
import org.springframework.stereotype.Component;

@Component
public class PermissaoGatewayJpa
        extends CrudGatewayJpa<Permissao, PermissaoRepository>
        implements PermissaoGateway {
}
