package com.ezequieljuliano.bookmark.web.dtos;

import com.ezequieljuliano.bookmark.domain.entities.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class RoleDto implements Serializable {

    @NotNull(message = "Perfil é uma informação obrigatória.")
    private Long id;
    private String name;
    private String description;

    public static RoleDto create(Role role) {
        RoleDto result = new RoleDto();
        result.setId(role.getId());
        result.setName(role.getName());
        result.setDescription(role.getDescription());
        return result;
    }

    public static List<RoleDto> createList(List<Role> roles) {
        List<RoleDto> result = new ArrayList<>();
        for (Role role : roles) {
            result.add(RoleDto.create(role));
        }
        return result;
    }

}
