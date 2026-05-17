package com.grupo6.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ResourceNotFoundExceptionTest {

    @Test
    void whenExceptionCreatedWithoutMessage_thenDefaultMessageIsUsed() {
        ResourceNotFoundException exception = new ResourceNotFoundException();

        assertEquals("Recurso no encontrado", exception.getMessage());
    }

    @Test
    void whenExceptionCreatedWithMessage_thenMessageIsPreserved() {
        String expected = "Profesor no encontrado";
        ResourceNotFoundException exception = new ResourceNotFoundException(expected);

        assertEquals(expected, exception.getMessage());
    }

    @Test
    void whenExceptionThrown_thenItsTypeIsResourceNotFoundException() {
        assertThrows(ResourceNotFoundException.class, () -> {
            throw new ResourceNotFoundException("Estudiante no encontrado");
        });
    }
}
