package com.swamini.exception;

public class StudentNotFound extends RuntimeException{
    public StudentNotFound(int id){
        super("Student with ID "+id+" not found.");
    }
}
