package br.com.ezequieljuliano.bookmark.application.extension;

import lombok.SneakyThrows;

import java.lang.reflect.Method;
import java.util.UUID;

public final class Ids {

    @SneakyThrows
    public static UUID getValue(Object object) {
        Class<?> clazz = object.getClass();
        Method method = clazz.getDeclaredMethod("getId");
        return (UUID) method.invoke(object);
    }

    @SneakyThrows
    public static void setValue(Object object, UUID value) {
        Class<?> clazz = object.getClass();
        Method method = clazz.getDeclaredMethod("setId", UUID.class);
        method.invoke(object, value);
    }

}
