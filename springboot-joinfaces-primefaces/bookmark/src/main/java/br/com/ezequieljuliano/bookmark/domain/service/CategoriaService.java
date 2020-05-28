package br.com.ezequieljuliano.bookmark.domain.service;

import br.com.ezequieljuliano.bookmark.domain.model.Categoria;
import br.com.ezequieljuliano.bookmark.domain.repository.CategoriaRepository;
import br.com.ezequieljuliano.bookmark.infrastructure.support.service.BaseCrudService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService extends BaseCrudService<Categoria, CategoriaRepository> {

    public List<Categoria> findByNomeContainingIgnoreCase(String nome) {
        return getRepository().findByNomeContainingIgnoreCase(nome);
    }

}
