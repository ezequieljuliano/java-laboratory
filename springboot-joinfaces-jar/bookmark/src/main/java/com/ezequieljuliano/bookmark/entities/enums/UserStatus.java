package com.ezequieljuliano.bookmark.entities.enums;

import java.io.Serializable;

public enum UserStatus implements Serializable {

    ACTIVE("Ativo"),
    INACTIVE("Inativo"),
    BLOCKED("Bloqueado");

    private final String description;

    UserStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public static UserStatus of(String description) {
        if (description != null) {
            for (UserStatus s : UserStatus.values()) {
                if (description.equalsIgnoreCase(s.getDescription())) {
                    return s;
                }
            }
        }
        return null;
    }

}
