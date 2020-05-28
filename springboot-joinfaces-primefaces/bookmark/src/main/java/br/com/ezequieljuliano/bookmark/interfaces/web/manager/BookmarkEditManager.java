package br.com.ezequieljuliano.bookmark.interfaces.web.manager;

import br.com.ezequieljuliano.bookmark.domain.model.Bookmark;
import br.com.ezequieljuliano.bookmark.domain.model.Categoria;
import br.com.ezequieljuliano.bookmark.domain.service.BookmarkService;
import br.com.ezequieljuliano.bookmark.domain.service.CategoriaService;
import br.com.ezequieljuliano.bookmark.infrastructure.annotation.EditPageView;
import br.com.ezequieljuliano.bookmark.infrastructure.annotation.ListPageView;
import br.com.ezequieljuliano.bookmark.infrastructure.support.web.manager.BaseEditManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
@ListPageView("bookmark-list.jsf")
@EditPageView("bookmark-edit.jsf")
public class BookmarkEditManager extends BaseEditManager<Bookmark, BookmarkService> implements Serializable {

    private final CategoriaService categoriaService;

    @Autowired
    public BookmarkEditManager(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    public List<Categoria> onCompleteCategoria(String nome) {
        return categoriaService.findByNomeContainingIgnoreCase(nome);
    }

}
