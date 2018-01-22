package com.ezequieljuliano.bookmark.utilities;

public enum HashAlgorithm {
    MD5("MD5"),
    SHA256("SHA-256");

    private final String text;

    private HashAlgorithm(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static HashAlgorithm fromText(String text) {
        if (text != null) {
            for (HashAlgorithm s : HashAlgorithm.values()) {
                if (text.equalsIgnoreCase(s.getText())) {
                    return s;
                }
            }
        }
        return null;
    }
}
