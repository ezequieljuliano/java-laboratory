package br.com.ezequieljuliano.bookmark.interfaces.web.manager;

import br.com.ezequieljuliano.bookmark.domain.model.Categoria;
import br.com.ezequieljuliano.bookmark.domain.service.CategoriaService;
import br.com.ezequieljuliano.bookmark.infrastructure.annotation.EditPageView;
import br.com.ezequieljuliano.bookmark.infrastructure.annotation.ListPageView;
import br.com.ezequieljuliano.bookmark.infrastructure.support.web.manager.BaseEditManager;
import org.springframework.stereotype.Component;

import javax.faces.view.ViewScoped;
import java.io.Serializable;

@Component
@ViewScoped
@ListPageView("categoria-list.jsf")
@EditPageView("categoria-edit.jsf")
public class CategoriaEditManager extends BaseEditManager<Categoria, CategoriaService> implements Serializable {

}
