package br.com.ezequieljuliano.bookmark.application.faces.manager.bookmark;

import br.com.ezequieljuliano.bookmark.application.faces.annotation.EditPageView;
import br.com.ezequieljuliano.bookmark.application.faces.annotation.ListPageView;
import br.com.ezequieljuliano.bookmark.application.faces.manager.EditManager;
import br.com.ezequieljuliano.bookmark.domain.entity.bookmark.Bookmark;
import br.com.ezequieljuliano.bookmark.domain.entity.bookmark.Categoria;
import br.com.ezequieljuliano.bookmark.domain.service.bookmark.BookmarkService;
import br.com.ezequieljuliano.bookmark.domain.service.bookmark.CategoriaService;
import jakarta.faces.view.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
@ListPageView("bookmark-list.jsf")
@EditPageView("bookmark-edit.jsf")
public class BookmarkEditManager extends EditManager<Bookmark, BookmarkService>
        implements Serializable {

    private final CategoriaService categoriaService;

    @Autowired
    public BookmarkEditManager(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    public List<Categoria> onCompleteCategoria(String nome) {
        return categoriaService.findByNomeContainingIgnoreCase(nome);
    }

}
