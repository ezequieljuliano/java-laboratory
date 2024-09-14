package br.com.ezequieljuliano.bookmark.infrastructure.jpa.bookmark;

import br.com.ezequieljuliano.bookmark.domain.entity.bookmark.Bookmark;
import br.com.ezequieljuliano.bookmark.domain.gateway.bookmark.BookmarkGateway;
import br.com.ezequieljuliano.bookmark.infrastructure.jpa.CrudGatewayJpa;
import br.com.ezequieljuliano.bookmark.infrastructure.jpa.bookmark.repository.BookmarkRepository;
import org.springframework.stereotype.Component;

@Component
public class BookmarkGatewayJpa
        extends CrudGatewayJpa<Bookmark, BookmarkRepository>
        implements BookmarkGateway {

}
