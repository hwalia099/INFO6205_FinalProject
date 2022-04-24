package com.tictactoe.springboottictactieapi.Exceptions;

public class DuplicatedKeyException extends Exception {

    public DuplicatedKeyException (String message) {
        super(message);
    }
}
