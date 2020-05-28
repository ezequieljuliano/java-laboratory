package br.com.ezequieljuliano.bookmark.interfaces.web.manager;

import br.com.ezequieljuliano.bookmark.domain.model.Bookmark;
import br.com.ezequieljuliano.bookmark.domain.service.BookmarkService;
import br.com.ezequieljuliano.bookmark.infrastructure.annotation.EditPageView;
import br.com.ezequieljuliano.bookmark.infrastructure.annotation.ListPageView;
import br.com.ezequieljuliano.bookmark.infrastructure.support.web.manager.BaseListManager;
import org.springframework.stereotype.Component;

import javax.faces.view.ViewScoped;
import java.io.Serializable;

@Component
@ViewScoped
@ListPageView("bookmark-list.jsf")
@EditPageView("bookmark-edit.jsf")
public class BookmarkListManager extends BaseListManager<Bookmark, BookmarkService> implements Serializable {

}
