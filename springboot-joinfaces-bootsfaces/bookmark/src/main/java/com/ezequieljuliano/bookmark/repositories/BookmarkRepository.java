package com.ezequieljuliano.bookmark.repositories;

import com.ezequieljuliano.bookmark.entities.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

}
