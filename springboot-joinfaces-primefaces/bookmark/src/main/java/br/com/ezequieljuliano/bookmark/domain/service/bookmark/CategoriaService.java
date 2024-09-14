package br.com.ezequieljuliano.bookmark.domain.service.bookmark;

import br.com.ezequieljuliano.bookmark.domain.entity.bookmark.Categoria;
import br.com.ezequieljuliano.bookmark.domain.gateway.bookmark.CategoriaGateway;
import br.com.ezequieljuliano.bookmark.domain.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService extends BaseService<Categoria, CategoriaGateway> {

    public List<Categoria> findByNomeContainingIgnoreCase(String nome) {
        return getGateway().findByNomeContainingIgnoreCase(nome);
    }

}
