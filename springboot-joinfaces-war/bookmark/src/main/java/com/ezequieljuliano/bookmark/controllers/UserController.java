package com.ezequieljuliano.bookmark.controllers;

import com.ezequieljuliano.bookmark.entities.Role;
import com.ezequieljuliano.bookmark.entities.User;
import com.ezequieljuliano.bookmark.entities.enums.UserStatus;
import com.ezequieljuliano.bookmark.repositories.RoleRepository;
import com.ezequieljuliano.bookmark.repositories.UserRepository;
import com.ezequieljuliano.bookmark.utilities.CrudController;
import com.ezequieljuliano.bookmark.utilities.MessageSeverity;
import com.ezequieljuliano.bookmark.utilities.PageList;
import com.ezequieljuliano.bookmark.utilities.PageView;
import com.ezequieljuliano.bookmark.utilities.Strings;
import com.ezequieljuliano.bookmark.utilities.ViewException;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Named
@ViewScoped
@PageList("user-list.jsf")
@PageView("user-view.jsf")
public class UserController extends CrudController<User, UserRepository> {

    @Autowired
    private RoleRepository roleRepository;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String passwordConfirmation;

    @Getter
    @Setter
    private String roleInformation;

    @Override
    protected void onSave(User user) {
        if (!isUpdateMode()) {
            if (Strings.isEmpty(password)) {
                throw new ViewException("Você deve informar uma senha.");
            }
            if (Strings.isEmpty(passwordConfirmation)) {
                throw new ViewException("Você deve informar uma confirmação senha.");
            }
        }
        if (!Strings.isEmpty(password) || !Strings.isEmpty(passwordConfirmation)) {
            if (!password.equals(passwordConfirmation)) {
                throw new ViewException("A senha informada não confere com a confirmação.");
            }
            user.setPassword(password);
        }
        password = null;
        passwordConfirmation = null;
        super.onSave(user);
    }

    public UserStatus[] getStatus() {
        return UserStatus.values();
    }

    public List<Role> getRolesList() {
        return roleRepository.findAll();
    }

    public void addRole() {
        try {
            if (!Strings.isEmpty(roleInformation)) {
                Role role = roleRepository.getOne(Strings.getId(roleInformation));
                if (!getRecord().containsRole(role)) {
                    getRecord().getRoles().add(role);
                } else {
                    getMessageContext().add("Este perfil já está adicionado para este usuário.", MessageSeverity.WARN);
                }
                roleInformation = null;
            } else {
                getMessageContext().add("Você deve selecionar um perfil.", MessageSeverity.WARN);
            }
        } catch (Exception e) {
            getMessageContext().add("Ocorreu um erro ao adicionar o perfil: {0}", MessageSeverity.ERROR, e.getMessage());
        }
    }

    public void removeRole(Role role) {
        try {
            if (role != null) {
                getRecord().getRoles().remove(role);
            }
        } catch (Exception e) {
            getMessageContext().add("Ocorreu um erro ao remover o perfil: {0}", MessageSeverity.ERROR, e.getMessage());
        }
    }

}