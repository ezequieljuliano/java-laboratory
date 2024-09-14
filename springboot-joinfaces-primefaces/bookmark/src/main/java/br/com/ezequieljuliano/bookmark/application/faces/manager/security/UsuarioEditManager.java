package br.com.ezequieljuliano.bookmark.application.faces.manager.security;

import br.com.ezequieljuliano.bookmark.application.faces.annotation.EditPageView;
import br.com.ezequieljuliano.bookmark.application.faces.annotation.ListPageView;
import br.com.ezequieljuliano.bookmark.application.faces.manager.EditManager;
import br.com.ezequieljuliano.bookmark.domain.entity.security.Papel;
import br.com.ezequieljuliano.bookmark.domain.entity.security.Usuario;
import br.com.ezequieljuliano.bookmark.domain.service.security.PapelService;
import br.com.ezequieljuliano.bookmark.domain.service.security.UsuarioService;
import jakarta.faces.view.ViewScoped;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
@ListPageView("usuario-list.jsf")
@EditPageView("usuario-edit.jsf")
public class UsuarioEditManager extends EditManager<Usuario, UsuarioService>
        implements Serializable {

    private final PapelService papelService;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String passwordConfirmacao;

    private List<Papel> papeis;

    @Autowired
    public UsuarioEditManager(PapelService papelService) {
        this.papelService = papelService;
    }

    public List<Papel> getPapeis() {
        if (papeis == null) {
            papeis = papelService.findAll(Sort.by(Sort.Direction.ASC, "nome"));
        }
        return papeis;
    }

    @Override
    protected void onBeforeSave(Usuario usuario) {
        getService().confirmarPassword(usuario, password, passwordConfirmacao);
        setPassword(null);
        setPasswordConfirmacao(null);
    }

}
