package br.com.ezequieljuliano.bookmark.infrastructure.jpa.bookmark.repository;

import br.com.ezequieljuliano.bookmark.domain.entity.bookmark.Bookmark;
import br.com.ezequieljuliano.bookmark.infrastructure.jpa.CrudRepositoryJpa;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkRepository extends CrudRepositoryJpa<Bookmark> {

}
