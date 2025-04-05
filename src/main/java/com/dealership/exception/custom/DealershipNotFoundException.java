package com.dealership.exception.custom;

public class DealershipNotFoundException extends RuntimeException {
    public DealershipNotFoundException(String message) {
        super(message);
    }
}
