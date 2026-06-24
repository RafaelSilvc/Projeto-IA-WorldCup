package com.copa2026.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Trata globalmente as exceções lançadas pelos Controllers/Services,
 * padronizando o formato de resposta de erro em toda a API.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Tratamento para quando um recurso não é encontrado pelo id (404 Not Found)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErroResposta> tratarResourceNotFound(ResourceNotFoundException ex) {
        ErroResposta erro = new ErroResposta(
                HttpStatus.NOT_FOUND.value(),
                "Recurso não encontrado",
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    // Tratamento para erros de validação dos campos do @RequestBody (@Valid) -> 400 Bad Request
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResposta> tratarValidacao(MethodArgumentNotValidException ex) {
        Map<String, String> erros = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(erroCampo ->
                erros.put(erroCampo.getField(), erroCampo.getDefaultMessage())
        );

        ErroResposta erro = new ErroResposta(
                HttpStatus.BAD_REQUEST.value(),
                "Erro de validação",
                "Um ou mais campos são inválidos. Verifique os detalhes.",
                erros
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    // Tratamento para argumentos inválidos genéricos (ex: regras de negócio violadas) -> 400 Bad Request
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErroResposta> tratarIllegalArgument(IllegalArgumentException ex) {
        ErroResposta erro = new ErroResposta(
                HttpStatus.BAD_REQUEST.value(),
                "Requisição inválida",
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    // Fallback para qualquer outra exceção não tratada explicitamente -> 500 Internal Server Error
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResposta> tratarErroGenerico(Exception ex) {
        ErroResposta erro = new ErroResposta(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno do servidor",
                "Ocorreu um erro inesperado: " + ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }
}
