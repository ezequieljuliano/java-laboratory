package com.ezequieljuliano.bookmark.repositories;

import com.ezequieljuliano.bookmark.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
