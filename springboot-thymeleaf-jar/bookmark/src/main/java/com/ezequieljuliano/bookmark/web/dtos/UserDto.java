package com.ezequieljuliano.bookmark.web.dtos;

import com.ezequieljuliano.bookmark.domain.entities.Role;
import com.ezequieljuliano.bookmark.domain.entities.User;
import com.ezequieljuliano.bookmark.infrastructure.tools.Strings;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto implements Serializable {

    private Long id;

    @NotEmpty(message = "Usuário é uma informação obrigatória.")
    @Size(max = 128, message = "Usuário deve ter um tamanho máximo de 128 caracteres.")
    private String username;

    @Size(max = 60, message = "Senha deve ter um tamanho máximo de 60 caracteres.")
    private String password;

    @Size(max = 60, message = "Confirmação de senha deve ter um tamanho máximo de 60 caracteres.")
    private String confirmation;

    @NotEmpty(message = "Nome é uma informação obrigatória.")
    @Size(max = 60, message = "Nome deve ter um tamanho máximo de 60 caracteres.")
    private String name;

    @NotEmpty(message = "E-mail é uma informação obrigatória.")
    @Size(max = 128, message = "E-mail deve ter um tamanho máximo de 128 caracteres.")
    @Email(message = "E-mail informado é inválido.")
    private String email;

    @NotNull(message = "Situação é uma informação obrigatória.")
    private User.Status status;

    private List<RoleDto> roles;

    public UserDto() {
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = null;
        this.confirmation = null;
        this.name = user.getName();
        this.email = user.getEmail();
        this.status = user.getStatus();
        for (Role role : user.getRoles()) {
            this.getRoles().add(new RoleDto(role));
        }
    }

    public List<RoleDto> getRoles() {
        if (roles == null)
            roles = new ArrayList<>();
        return roles;
    }

    public void toEntity(User user) {
        user.setUsername(this.username);
        if (!Strings.isEmpty(this.password)) {
            user.setPassword(this.password);
        }
        user.setName(this.name);
        user.setEmail(this.email);
        user.setStatus(this.status);
    }

    public User asEntity() {
        User user = new User();
        toEntity(user);
        return user;
    }
}
