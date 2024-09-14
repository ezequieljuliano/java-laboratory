package br.com.ezequieljuliano.bookmark.domain.service.security;

import br.com.ezequieljuliano.bookmark.domain.entity.security.Papel;
import br.com.ezequieljuliano.bookmark.domain.gateway.security.PapelGateway;
import br.com.ezequieljuliano.bookmark.domain.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PapelService extends BaseService<Papel, PapelGateway> {

    public List<Papel> findByNomeContainingIgnoreCase(String nome) {
        return getGateway().findByNomeContainingIgnoreCase(nome);
    }

}
