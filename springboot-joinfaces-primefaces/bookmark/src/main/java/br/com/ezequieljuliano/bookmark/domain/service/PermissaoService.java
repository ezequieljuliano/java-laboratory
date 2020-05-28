package br.com.ezequieljuliano.bookmark.domain.service;

import br.com.ezequieljuliano.bookmark.domain.model.Permissao;
import br.com.ezequieljuliano.bookmark.domain.repository.PermissaoRepository;
import br.com.ezequieljuliano.bookmark.infrastructure.support.service.BaseCrudService;
import org.springframework.stereotype.Service;

@Service
public class PermissaoService extends BaseCrudService<Permissao, PermissaoRepository> {

}
