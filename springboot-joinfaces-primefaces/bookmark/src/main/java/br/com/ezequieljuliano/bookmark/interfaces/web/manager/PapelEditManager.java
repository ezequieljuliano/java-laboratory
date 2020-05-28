package br.com.ezequieljuliano.bookmark.interfaces.web.manager;

import br.com.ezequieljuliano.bookmark.domain.model.Papel;
import br.com.ezequieljuliano.bookmark.domain.model.Permissao;
import br.com.ezequieljuliano.bookmark.domain.service.PapelService;
import br.com.ezequieljuliano.bookmark.domain.service.PermissaoService;
import br.com.ezequieljuliano.bookmark.infrastructure.annotation.EditPageView;
import br.com.ezequieljuliano.bookmark.infrastructure.annotation.ListPageView;
import br.com.ezequieljuliano.bookmark.infrastructure.support.web.manager.BaseEditManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
@ListPageView("papel-list.jsf")
@EditPageView("papel-edit.jsf")
public class PapelEditManager extends BaseEditManager<Papel, PapelService> implements Serializable {

    private final PermissaoService permissaoService;
    private List<Permissao> permissoes;

    @Autowired
    public PapelEditManager(PermissaoService permissaoService) {
        this.permissaoService = permissaoService;
    }

    public List<Permissao> getPermissoes() {
        if (permissoes == null) {
            permissoes = permissaoService.findAll(Sort.by(Sort.Direction.ASC, "recurso", "acao"));
        }
        return permissoes;
    }

}
