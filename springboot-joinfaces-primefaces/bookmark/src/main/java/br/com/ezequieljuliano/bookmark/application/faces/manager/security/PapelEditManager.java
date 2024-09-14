package br.com.ezequieljuliano.bookmark.application.faces.manager.security;

import br.com.ezequieljuliano.bookmark.application.faces.annotation.EditPageView;
import br.com.ezequieljuliano.bookmark.application.faces.annotation.ListPageView;
import br.com.ezequieljuliano.bookmark.application.faces.manager.EditManager;
import br.com.ezequieljuliano.bookmark.domain.entity.security.Papel;
import br.com.ezequieljuliano.bookmark.domain.entity.security.Permissao;
import br.com.ezequieljuliano.bookmark.domain.service.security.PapelService;
import br.com.ezequieljuliano.bookmark.domain.service.security.PermissaoService;
import jakarta.faces.view.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
@ListPageView("papel-list.jsf")
@EditPageView("papel-edit.jsf")
public class PapelEditManager extends EditManager<Papel, PapelService>
        implements Serializable {

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
