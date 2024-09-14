package br.com.ezequieljuliano.bookmark.infrastructure.jpa.security;

import br.com.ezequieljuliano.bookmark.domain.entity.security.Papel;
import br.com.ezequieljuliano.bookmark.domain.gateway.security.PapelGateway;
import br.com.ezequieljuliano.bookmark.infrastructure.jpa.CrudGatewayJpa;
import br.com.ezequieljuliano.bookmark.infrastructure.jpa.security.repository.PapelRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PapelGatewayJpa
        extends CrudGatewayJpa<Papel, PapelRepository>
        implements PapelGateway {

    @Override
    public List<Papel> findByNomeContainingIgnoreCase(String nome) {
        return getRepository().findByNomeContainingIgnoreCase(nome);
    }
}
