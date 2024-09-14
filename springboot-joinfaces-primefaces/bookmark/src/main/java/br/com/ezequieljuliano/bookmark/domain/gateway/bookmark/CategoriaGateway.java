package br.com.ezequieljuliano.bookmark.domain.gateway.bookmark;

import br.com.ezequieljuliano.bookmark.domain.entity.bookmark.Categoria;
import br.com.ezequieljuliano.bookmark.domain.gateway.CrudGateway;

import java.util.List;

public interface CategoriaGateway extends CrudGateway<Categoria> {

    List<Categoria> findByNomeContainingIgnoreCase(String nome);

}
