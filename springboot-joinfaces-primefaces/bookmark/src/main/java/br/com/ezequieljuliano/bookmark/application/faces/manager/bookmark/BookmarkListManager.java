package br.com.ezequieljuliano.bookmark.application.faces.manager.bookmark;

import br.com.ezequieljuliano.bookmark.application.faces.annotation.EditPageView;
import br.com.ezequieljuliano.bookmark.application.faces.annotation.ListPageView;
import br.com.ezequieljuliano.bookmark.application.faces.manager.ListManager;
import br.com.ezequieljuliano.bookmark.domain.entity.bookmark.Bookmark;
import br.com.ezequieljuliano.bookmark.domain.service.bookmark.BookmarkService;
import jakarta.faces.view.ViewScoped;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@ViewScoped
@ListPageView("bookmark-list.jsf")
@EditPageView("bookmark-edit.jsf")
public class BookmarkListManager extends ListManager<Bookmark, BookmarkService>
        implements Serializable {

}
