package com.github.shinwaffle.discordbot.exceptions;

public class ClientIDMissingException extends RuntimeException {
    public ClientIDMissingException(String errorMessage) {
        super(errorMessage);
    }
}