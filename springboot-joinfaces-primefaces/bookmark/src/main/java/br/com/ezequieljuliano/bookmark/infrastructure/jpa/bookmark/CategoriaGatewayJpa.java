package br.com.ezequieljuliano.bookmark.infrastructure.jpa.bookmark;

import br.com.ezequieljuliano.bookmark.domain.entity.bookmark.Categoria;
import br.com.ezequieljuliano.bookmark.domain.gateway.bookmark.CategoriaGateway;
import br.com.ezequieljuliano.bookmark.infrastructure.jpa.CrudGatewayJpa;
import br.com.ezequieljuliano.bookmark.infrastructure.jpa.bookmark.repository.CategoriaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoriaGatewayJpa
        extends CrudGatewayJpa<Categoria, CategoriaRepository>
        implements CategoriaGateway {

    @Override
    public List<Categoria> findByNomeContainingIgnoreCase(String nome) {
        return getRepository().findByNomeContainingIgnoreCase(nome);
    }
}
