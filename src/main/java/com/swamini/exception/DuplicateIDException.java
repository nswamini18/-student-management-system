package com.swamini.exception;

public class DuplicateIDException extends RuntimeException {
    public DuplicateIDException(int id) {
        super("❌ Student with ID " + id + " already exists.");
    }
}
