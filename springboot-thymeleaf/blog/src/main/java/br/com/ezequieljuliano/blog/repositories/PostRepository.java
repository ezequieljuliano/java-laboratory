package br.com.ezequieljuliano.blog.repositories;

import br.com.ezequieljuliano.blog.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
