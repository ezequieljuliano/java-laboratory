package com.ezequieljuliano.bookmark.web.controllers;

import com.ezequieljuliano.bookmark.domain.entities.Role;
import com.ezequieljuliano.bookmark.domain.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/roles")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("roles", roleRepository.findAll());
        return "role-list";
    }

    @GetMapping("/create")
    public String create(Model model, Role role) {
        model.addAttribute("role", role);
        return "role-view";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        return create(model, roleRepository.getOne(id));
    }

    @GetMapping("/{id}/delete")
    public String delete(Model model, @PathVariable("id") Long id) {
        roleRepository.deleteById(id);
        return "redirect:/roles";
    }

    @PostMapping("/create")
    public String save(Model model, @Valid @ModelAttribute("role") Role role, BindingResult result) {
        if (result.hasErrors()) {
            return create(model, role);
        }
        roleRepository.save(role);
        return "redirect:/roles";
    }

}
