package br.com.ezequieljuliano.bookmark.infrastructure.utilities;

import lombok.SneakyThrows;

public final class EntityUtils {

    @SneakyThrows
    public static boolean isUpdateMode(Object entity) {
        return (IdUtils.getValue(entity) != null);
    }

}
