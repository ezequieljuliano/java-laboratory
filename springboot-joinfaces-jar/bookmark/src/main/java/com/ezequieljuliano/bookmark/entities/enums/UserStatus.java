package com.ezequieljuliano.bookmark.entities.enums;

import java.io.Serializable;

public enum UserStatus implements Serializable {

    ACTIVE("Ativo"),
    INACTIVE("Inativo"),
    BLOCKED("Bloqueado");

    private final String text;

    private UserStatus(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static UserStatus fromText(String text) {
        if (text != null) {
            for (UserStatus s : UserStatus.values()) {
                if (text.equalsIgnoreCase(s.getText())) {
                    return s;
                }
            }
        }
        return null;
    }

}
