package com.ezequieljuliano.bookmark.web.controllers;

import com.ezequieljuliano.bookmark.domain.entities.Role;
import com.ezequieljuliano.bookmark.domain.entities.User;
import com.ezequieljuliano.bookmark.domain.repositories.RoleRepository;
import com.ezequieljuliano.bookmark.domain.repositories.UserRepository;
import com.ezequieljuliano.bookmark.infrastructure.tools.Strings;
import com.ezequieljuliano.bookmark.web.dtos.RoleDto;
import com.ezequieljuliano.bookmark.web.dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("users", UserDto.createList(userRepository.findAll()));
        return "user-list";
    }

    @GetMapping("/create")
    public String create(Model model, UserDto user) {
        model.addAttribute("user", user);
        model.addAttribute("statusValues", User.Status.values());
        model.addAttribute("role", new RoleDto());
        model.addAttribute("roles", RoleDto.createList(roleRepository.findAll()));
        return "user-view";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        return create(model, UserDto.create(userRepository.getOne(id)));
    }

    @GetMapping("/{id}/delete")
    public String delete(Model model, @PathVariable("id") Long id) {
        userRepository.deleteById(id);
        return "redirect:/users";
    }

    @PostMapping("/create")
    public String save(Model model, @Valid @ModelAttribute("user") UserDto user, BindingResult result) {
        if (user.getId() == null) {
            if (Strings.isEmpty(user.getPassword())) {
                result.addError(new ObjectError("password", "Você deve informar uma senha."));
            }
            if (Strings.isEmpty(user.getConfirmation())) {
                result.addError(new ObjectError("confirmation", "Você deve informar uma confirmação de senha."));
            }
        }

        if (!Strings.isEmpty(user.getPassword()) || !Strings.isEmpty(user.getConfirmation())) {
            if (!user.getPassword().equals(user.getConfirmation())) {
                result.addError(new ObjectError("confirmation", "A senha informada não confere com a sua respectiva confirmação."));
            }
        }

        if (result.hasErrors()) {
            return create(model, user);
        }

        User entity;
        if (user.getId() == null) {
            entity = user.asEntity();
        } else {
            entity = userRepository.getOne(user.getId());
            user.toEntity(entity);
        }

        userRepository.save(entity);
        return "redirect:/users";
    }

    @GetMapping("/{id}/roles/{roleId}/delete")
    public String deleteRole(Model model, @PathVariable("id") Long id, @PathVariable("roleId") Long roleId) {
        User user = userRepository.getOne(id);

        for (int i = 0; i < user.getRoles().size(); i++) {
            if (user.getRoles().get(i).getId().equals(roleId)) {
                user.getRoles().remove(i);
                break;
            }
        }

        userRepository.save(user);
        return "redirect:/users/".concat(id.toString()).concat("/edit");
    }

    @PostMapping("/{id}/roles/create")
    public String saveRole(Model model, @PathVariable("id") Long id, @Valid @ModelAttribute("role") RoleDto role) {
        User user = userRepository.getOne(id);

        boolean existsRole = false;
        for (int i = 0; i < user.getRoles().size(); i++) {
            if (user.getRoles().get(i).getId().equals(role.getId())) {
                existsRole = true;
                break;
            }
        }

        if (!existsRole) {
            Role entity = roleRepository.getOne(role.getId());
            user.getRoles().add(entity);
        }

        userRepository.save(user);
        return "redirect:/users/".concat(id.toString()).concat("/edit");
    }

}