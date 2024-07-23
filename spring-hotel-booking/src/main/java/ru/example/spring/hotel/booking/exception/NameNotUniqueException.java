package ru.example.spring.hotel.booking.exception;

public class NameNotUniqueException extends RuntimeException {
    public NameNotUniqueException(String message) {
        super(message);
    }
}
