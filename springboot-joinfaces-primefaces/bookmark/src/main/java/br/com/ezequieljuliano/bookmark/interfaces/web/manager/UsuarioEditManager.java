package br.com.ezequieljuliano.bookmark.interfaces.web.manager;

import br.com.ezequieljuliano.bookmark.domain.model.Papel;
import br.com.ezequieljuliano.bookmark.domain.model.Usuario;
import br.com.ezequieljuliano.bookmark.domain.service.PapelService;
import br.com.ezequieljuliano.bookmark.domain.service.UsuarioService;
import br.com.ezequieljuliano.bookmark.infrastructure.annotation.EditPageView;
import br.com.ezequieljuliano.bookmark.infrastructure.annotation.ListPageView;
import br.com.ezequieljuliano.bookmark.infrastructure.support.web.manager.BaseEditManager;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
@ListPageView("usuario-list.jsf")
@EditPageView("usuario-edit.jsf")
public class UsuarioEditManager extends BaseEditManager<Usuario, UsuarioService> implements Serializable {

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
