package br.com.ezequieljuliano.bookmark.domain.service;

import br.com.ezequieljuliano.bookmark.domain.model.Papel;
import br.com.ezequieljuliano.bookmark.domain.repository.PapelRepository;
import br.com.ezequieljuliano.bookmark.infrastructure.support.service.BaseCrudService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PapelService extends BaseCrudService<Papel, PapelRepository> {

    public List<Papel> findByNomeContainingIgnoreCase(String nome) {
        return getRepository().findByNomeContainingIgnoreCase(nome);
    }

}
