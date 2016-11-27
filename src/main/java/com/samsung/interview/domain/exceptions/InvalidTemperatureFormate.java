package com.samsung.interview.domain.exceptions;

public class InvalidTemperatureFormate extends Exception {
    public InvalidTemperatureFormate(String message) {
        super(message);
    }

    public InvalidTemperatureFormate(String message, Throwable cause) {
        super(message, cause);
    }
}
