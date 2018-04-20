package com.ezequieljuliano.bookmark.domain.repositories;

import com.ezequieljuliano.bookmark.domain.entities.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

}
