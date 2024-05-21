package com.mftplus.book.exception;

public class NoBookException extends ExceptionPattern{

    public NoBookException(String message) {
        super(message);
        setStatusCode(404);
    }
}
