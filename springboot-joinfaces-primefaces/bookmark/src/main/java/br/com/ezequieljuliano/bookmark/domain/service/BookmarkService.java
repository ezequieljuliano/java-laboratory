package br.com.ezequieljuliano.bookmark.domain.service;

import br.com.ezequieljuliano.bookmark.domain.model.Bookmark;
import br.com.ezequieljuliano.bookmark.domain.repository.BookmarkRepository;
import br.com.ezequieljuliano.bookmark.infrastructure.support.service.BaseCrudService;
import org.springframework.stereotype.Service;

@Service
public class BookmarkService extends BaseCrudService<Bookmark, BookmarkRepository> {

}
