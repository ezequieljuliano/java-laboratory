package com.ezequieljuliano.bookmark.utilities;

public interface MessageContext {

    public void add(String message, MessageSeverity severity, Object... args);

    public void add(String message, MessageSeverity severity);

    public void add(String message, Object... args);

    public void add(String message);

}
