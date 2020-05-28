package com.ezequieljuliano.bookmark.web.controllers;

import com.ezequieljuliano.bookmark.domain.entities.Bookmark;
import com.ezequieljuliano.bookmark.domain.repositories.BookmarkRepository;
import com.ezequieljuliano.bookmark.domain.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/bookmarks")
public class BookmarkController {

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("bookmarks", bookmarkRepository.findAll());
        return "bookmark-list";
    }

    @GetMapping("/create")
    public String create(Model model, Bookmark bookmark) {
        model.addAttribute("bookmark", bookmark);
        model.addAttribute("categories", categoryRepository.findAll());
        return "bookmark-view";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        return create(model, bookmarkRepository.getOne(id));
    }

    @GetMapping("/{id}/delete")
    public String delete(Model model, @PathVariable("id") Long id) {
        bookmarkRepository.deleteById(id);
        return "redirect:/bookmarks";
    }

    @PostMapping("/create")
    public String save(Model model, @Valid @ModelAttribute("bookmark") Bookmark bookmark, BindingResult result) {
        if (result.hasErrors()) {
            return create(model, bookmark);
        }
        bookmarkRepository.save(bookmark);
        return "redirect:/bookmarks";
    }

}
