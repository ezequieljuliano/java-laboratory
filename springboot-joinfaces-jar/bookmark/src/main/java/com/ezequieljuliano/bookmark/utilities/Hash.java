package com.ezequieljuliano.bookmark.utilities;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Hash {

    private String checksum;

    private String bytesToString(byte[] input) {
        StringBuilder result = new StringBuilder();
        char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        for (int idx = 0; idx < input.length; ++idx) {
            byte b = input[idx];
            result.append(digits[(b & 0xf0) >> 4]);
            result.append(digits[b & 0x0f]);
        }
        return result.toString();
    }

    public Hash(String value, HashAlgorithm algorithm) {
        checksum = "";
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm.getText());
            md.update(value.getBytes(Charset.forName("UTF-8")));
            checksum = bytesToString(md.digest());
        } catch (NoSuchAlgorithmException e) {
            checksum = "";
        }
    }

    public String getChecksum() {
        return checksum;
    }

}
