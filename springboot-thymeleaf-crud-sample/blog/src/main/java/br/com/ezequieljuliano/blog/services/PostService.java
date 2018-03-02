package br.com.ezequieljuliano.blog.services;

import br.com.ezequieljuliano.blog.entities.Post;
import br.com.ezequieljuliano.blog.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    public List<Post> findAll() {
        return repository.findAll();
    }

    public Post findOne(Long id) {
        return repository.getOne(id);
    }

    public Post save(Post post) {
        return repository.saveAndFlush(post);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
