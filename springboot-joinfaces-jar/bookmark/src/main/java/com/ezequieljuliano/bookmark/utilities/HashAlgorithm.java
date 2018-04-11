package com.ezequieljuliano.bookmark.utilities;

public enum HashAlgorithm {

    MD5("MD5"),
    SHA256("SHA-256");

    private final String description;

    HashAlgorithm(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public static HashAlgorithm of(String description) {
        if (description != null) {
            for (HashAlgorithm s : HashAlgorithm.values()) {
                if (description.equalsIgnoreCase(s.getDescription())) {
                    return s;
                }
            }
        }
        return null;
    }
}
