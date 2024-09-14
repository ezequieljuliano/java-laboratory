package br.com.ezequieljuliano.bookmark.domain.gateway.security;

import br.com.ezequieljuliano.bookmark.domain.entity.security.Papel;
import br.com.ezequieljuliano.bookmark.domain.gateway.CrudGateway;

import java.util.List;

public interface PapelGateway extends CrudGateway<Papel> {

    List<Papel> findByNomeContainingIgnoreCase(String nome);

}
