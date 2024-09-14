package br.com.ezequieljuliano.bookmark.domain.service.bookmark;

import br.com.ezequieljuliano.bookmark.domain.entity.bookmark.Bookmark;
import br.com.ezequieljuliano.bookmark.domain.gateway.bookmark.BookmarkGateway;
import br.com.ezequieljuliano.bookmark.domain.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class BookmarkService extends BaseService<Bookmark, BookmarkGateway> {

}
