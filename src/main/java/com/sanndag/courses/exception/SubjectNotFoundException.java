package com.sanndag.courses.exception;

public class SubjectNotFoundException extends RuntimeException{
    public SubjectNotFoundException (String message){
        super(message);
    }
}
