package br.com.ezequieljuliano.bookmark.application.extension;

import lombok.SneakyThrows;

public final class Entities {

    @SneakyThrows
    public static boolean isUpdateMode(Object entity) {
        return (Ids.getValue(entity) != null);
    }

}
