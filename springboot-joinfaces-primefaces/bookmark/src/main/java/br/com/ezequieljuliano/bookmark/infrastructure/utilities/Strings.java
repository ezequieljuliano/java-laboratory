package br.com.ezequieljuliano.bookmark.infrastructure.utilities;

public final class Strings {

    public static boolean isEmpty(String string) {
        return string == null || string.trim().isEmpty();
    }

}
