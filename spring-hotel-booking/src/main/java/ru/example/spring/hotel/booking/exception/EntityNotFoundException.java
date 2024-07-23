package ru.example.spring.hotel.booking.exception;

import java.text.MessageFormat;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Long id, Class<?> clazz) {
        super(MessageFormat.format("Entity {1} with id={0} not found", id, clazz.getSimpleName()));
    }

}
