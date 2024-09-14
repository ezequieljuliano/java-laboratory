package br.com.ezequieljuliano.bookmark.domain.service.security;

import br.com.ezequieljuliano.bookmark.domain.entity.security.Permissao;
import br.com.ezequieljuliano.bookmark.domain.gateway.security.PermissaoGateway;
import br.com.ezequieljuliano.bookmark.domain.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class PermissaoService extends BaseService<Permissao, PermissaoGateway> {

}
