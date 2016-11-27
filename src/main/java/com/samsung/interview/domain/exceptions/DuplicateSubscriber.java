package com.samsung.interview.domain.exceptions;

public class DuplicateSubscriber extends Exception {
    public DuplicateSubscriber(String message) {
        super(message);
    }
}
