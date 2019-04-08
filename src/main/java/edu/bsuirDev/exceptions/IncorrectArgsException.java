package edu.bsuirDev.exceptions;

/*
 * must used in situations, where input args have incorrect values
 * */
public class IncorrectArgsException extends IllegalArgumentException {
    public IncorrectArgsException(String message) {
        super(message);
    }
}
