package br.com.ezequieljuliano.bookmark.application.faces.manager.bookmark;

import br.com.ezequieljuliano.bookmark.application.faces.annotation.EditPageView;
import br.com.ezequieljuliano.bookmark.application.faces.annotation.ListPageView;
import br.com.ezequieljuliano.bookmark.application.faces.manager.EditManager;
import br.com.ezequieljuliano.bookmark.domain.entity.bookmark.Categoria;
import br.com.ezequieljuliano.bookmark.domain.service.bookmark.CategoriaService;
import jakarta.faces.view.ViewScoped;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@ViewScoped
@ListPageView("categoria-list.jsf")
@EditPageView("categoria-edit.jsf")
public class CategoriaEditManager extends EditManager<Categoria, CategoriaService>
        implements Serializable {

}
