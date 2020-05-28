package br.com.ezequieljuliano.bookmark.interfaces.web.manager;

import br.com.ezequieljuliano.bookmark.domain.model.Usuario;
import br.com.ezequieljuliano.bookmark.domain.service.UsuarioService;
import br.com.ezequieljuliano.bookmark.infrastructure.annotation.EditPageView;
import br.com.ezequieljuliano.bookmark.infrastructure.annotation.ListPageView;
import br.com.ezequieljuliano.bookmark.infrastructure.support.web.manager.BaseListManager;
import org.springframework.stereotype.Component;

import javax.faces.view.ViewScoped;
import java.io.Serializable;

@Component
@ViewScoped
@ListPageView("usuario-list.jsf")
@EditPageView("usuario-edit.jsf")
public class UsuarioListManager extends BaseListManager<Usuario, UsuarioService> implements Serializable {

}
