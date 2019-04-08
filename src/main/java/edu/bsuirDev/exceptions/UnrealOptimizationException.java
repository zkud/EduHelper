package edu.bsuirDev.exceptions;

/*
 * occures when optimization is impossible
 * */
public class UnrealOptimizationException extends RuntimeException {
    public UnrealOptimizationException(String message) {
        super(message);
    }
}
