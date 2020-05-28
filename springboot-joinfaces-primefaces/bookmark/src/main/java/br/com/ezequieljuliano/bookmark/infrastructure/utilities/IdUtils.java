package br.com.ezequieljuliano.bookmark.infrastructure.utilities;

import lombok.SneakyThrows;

import java.lang.reflect.Method;
import java.util.UUID;

public final class IdUtils {

    @SneakyThrows
    public static UUID getValue(Object object) {
        Class<?> clazz = object.getClass();
        if (clazz != null) {
            Method method = clazz.getDeclaredMethod("getId");
            return (UUID) method.invoke(object);
        }
        return null;
    }

    @SneakyThrows
    public static void setValue(Object object, UUID value) {
        Class<?> clazz = object.getClass();
        if (clazz != null) {
            Method method = clazz.getDeclaredMethod("setId", UUID.class);
            method.invoke(object, value);
        }
    }

}
