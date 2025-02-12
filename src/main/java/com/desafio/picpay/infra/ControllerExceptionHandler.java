package com.desafio.picpay.infra;

import com.desafio.picpay.dto.ExceptionDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity threatDuplicateEntity(DataIntegrityViolationException ex) {
        ExceptionDto exceptionDto = new ExceptionDto("Usuário já cadastrado.", "400");

        return ResponseEntity.badRequest().body(exceptionDto);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity threat404(EntityNotFoundException ex) {

        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity threatGeneralExceptions(Exception ex) {
        ExceptionDto exceptionDto = new ExceptionDto(ex.getMessage(), "500");

        return ResponseEntity.internalServerError().body(exceptionDto);
    }
}
