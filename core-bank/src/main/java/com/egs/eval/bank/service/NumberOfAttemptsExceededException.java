package com.egs.eval.bank.service;

public class NumberOfAttemptsExceededException extends RuntimeException {
    public NumberOfAttemptsExceededException(String message) {
        super(message);
    }
}
