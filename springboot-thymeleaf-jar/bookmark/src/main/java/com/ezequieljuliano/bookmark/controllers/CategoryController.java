package com.ezequieljuliano.bookmark.controllers;

import com.ezequieljuliano.bookmark.entities.Category;
import com.ezequieljuliano.bookmark.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "category-list";
    }

    @GetMapping("/create")
    public String create(Model model, Category category) {
        model.addAttribute("category", category);
        return "category-view";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        return create(model, categoryRepository.getOne(id));
    }

    @GetMapping("/{id}/delete")
    public String delete(Model model, @PathVariable("id") Long id) {
        categoryRepository.deleteById(id);
        return "redirect:/categories";
    }

    @PostMapping("/create")
    public String save(Model model, @Valid @ModelAttribute("category") Category category, BindingResult result) {
        if (result.hasErrors()) {
            return create(model, category);
        }
        categoryRepository.save(category);
        return "redirect:/categories";
    }

}
