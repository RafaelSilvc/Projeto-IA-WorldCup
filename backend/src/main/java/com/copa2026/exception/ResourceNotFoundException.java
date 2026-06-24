package com.copa2026.exception;

/**
 * Exceção lançada quando um recurso não é encontrado no banco de dados
 * (ex: GET/PUT/DELETE por um id que não existe).
 * É tratada globalmente pelo GlobalExceptionHandler, retornando HTTP 404.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
