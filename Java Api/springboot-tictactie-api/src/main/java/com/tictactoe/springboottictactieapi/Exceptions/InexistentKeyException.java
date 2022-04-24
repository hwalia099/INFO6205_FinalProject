package com.tictactoe.springboottictactieapi.Exceptions;

public class InexistentKeyException extends Exception {
    public InexistentKeyException (String message) {
        super(message);
    }
}
