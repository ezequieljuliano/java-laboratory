package com.ezequieljuliano.bookmark.utilities;

public interface MessageContext {

    void add(String message, MessageSeverity severity, Object... args);

    void add(String message, MessageSeverity severity);

    void add(String message, Object... args);

    void add(String message);

}
