package com.example.aula6_mercado_spring.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> illegalArgumentException(IllegalArgumentException erro){
        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(Map.of(
                "mensagem", erro.getMessage(),
                "sucesso", false
            ));
    }
    @ExceptionHandler(RuntimeException.class)
        public ResponseEntity<Map<String, Object>> runTimeException(RuntimeException erro){
            return ResponseEntity
                .badRequest()
                .body(Map.of(
                    "mensagem", erro.getMessage(),
                    "sucesso", false
                ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> methodArgumentNotValidExceptio(MethodArgumentNotValidException erro){
        return ResponseEntity
            .badRequest()
            .body(Map.of(
                "mensagem", erro.getFieldErrors().get(0).getDefaultMessage(),
                "sucesso", false
            ));
    }

    // Captura todas as exceções não mapeadas e retorna uma mensagem genérica.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleAllExceptions(Exception erro) {
        return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("mensagem", "Erro no servidor"));
    }
}

