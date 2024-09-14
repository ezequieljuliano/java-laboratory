package br.com.ezequieljuliano.bookmark.application.faces.manager.security;

import br.com.ezequieljuliano.bookmark.application.faces.annotation.EditPageView;
import br.com.ezequieljuliano.bookmark.application.faces.annotation.ListPageView;
import br.com.ezequieljuliano.bookmark.application.faces.manager.ListManager;
import br.com.ezequieljuliano.bookmark.domain.entity.security.Usuario;
import br.com.ezequieljuliano.bookmark.domain.service.security.UsuarioService;
import jakarta.faces.view.ViewScoped;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@ViewScoped
@ListPageView("usuario-list.jsf")
@EditPageView("usuario-edit.jsf")
public class UsuarioListManager extends ListManager<Usuario, UsuarioService>
        implements Serializable {

}
