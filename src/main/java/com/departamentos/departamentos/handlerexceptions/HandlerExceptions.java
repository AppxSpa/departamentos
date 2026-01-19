package com.departamentos.departamentos.handlerexceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.departamentos.departamentos.dto.ErrorResponse;
import com.departamentos.departamentos.exceptions.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class HandlerExceptions {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handlerNotFoundException(NotFoundException e, HttpServletRequest request) {

        ErrorResponse error = maptoErrorResponse(e, request, HttpStatus.NOT_FOUND);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }

    private <T extends Exception> ErrorResponse maptoErrorResponse(T e, HttpServletRequest request, HttpStatus status) {

        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .mensaje(e.getMessage())
                .ruta(request.getRequestURI())
                .build();

    }

}
