package com.departamentos.departamentos.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String mensaje) {
        super(mensaje);
    }

}
