package com.ezequieljuliano.bookmark.web.dtos;

import com.ezequieljuliano.bookmark.domain.entities.Role;
import com.ezequieljuliano.bookmark.domain.entities.User;
import com.ezequieljuliano.bookmark.infrastructure.tools.Strings;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
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

    public List<RoleDto> getRoles() {
        if (roles == null)
            roles = new ArrayList<>();
        return roles;
    }

    public String getRolesDescriptions() {
        String descriptions = "";
        for (int i = 0; i < getRoles().size(); i++) {
            descriptions = descriptions.concat(getRoles().get(i).getDescription());
            if (i < (getRoles().size() - 1)) {
                descriptions = descriptions.concat(", ");
            }
        }
        return descriptions;
    }

    public static UserDto create(User user) {
        UserDto result = new UserDto();
        result.setId(user.getId());
        result.setUsername(user.getUsername());
        result.setPassword(null);
        result.setConfirmation(null);
        result.setName(user.getName());
        result.setEmail(user.getEmail());
        result.setStatus(user.getStatus());
        for (Role role : user.getRoles()) {
            result.getRoles().add(RoleDto.create(role));
        }
        return result;
    }

    public static List<UserDto> createList(List<User> users) {
        List<UserDto> result = new ArrayList<>();
        for (User user : users) {
            result.add(UserDto.create(user));
        }
        return result;
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
