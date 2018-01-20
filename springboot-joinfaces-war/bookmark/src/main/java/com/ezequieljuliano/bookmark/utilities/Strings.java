package com.ezequieljuliano.bookmark.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Strings {

    public static boolean isEmpty(String string) {
        return string == null || string.trim().isEmpty();
    }

    public static Long getId(String string) {
        String id = "";
        Pattern p = Pattern.compile("\\(([^)]+)\\)");
        Matcher m = p.matcher(string);
        while (m.find()) {
            id = m.group(1);
        }
        if (!Strings.isEmpty(id)) {
            return Long.parseLong(id);
        }
        return null;
    }

}
