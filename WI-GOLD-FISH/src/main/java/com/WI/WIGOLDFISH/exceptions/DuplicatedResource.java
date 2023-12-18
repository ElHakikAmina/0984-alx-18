package com.WI.WIGOLDFISH.exceptions;

public class DuplicatedResource extends RuntimeException{
    public DuplicatedResource(String message) {
        super(message);
    }
}