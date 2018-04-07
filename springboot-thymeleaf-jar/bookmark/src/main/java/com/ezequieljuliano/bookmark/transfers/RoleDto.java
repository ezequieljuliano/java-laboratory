package com.ezequieljuliano.bookmark.transfers;

import com.ezequieljuliano.bookmark.entities.Role;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class RoleDto implements Serializable {

    @NotNull(message = "Perfil é uma informação obrigatória.")
    private Long id;

    private String name;
    private String description;

    public RoleDto() {
    }

    public RoleDto(Role role) {
        this.id = role.getId();
        this.name = role.getName();
        this.description = role.getDescription();
    }

    public List<RoleDto> asList(List<Role> roles) {
        List<RoleDto> list = new ArrayList<>();
        for (Role role : roles) {
            list.add(new RoleDto(role));
        }
        return list;
    }

}
