package com.swamini.exception;

public class InvalidCredentials extends RuntimeException {
    public InvalidCredentials() {
        super("❌ Invalid username or password.");
    }
}