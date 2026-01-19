package com.departamentos.departamentos.utils;

import java.util.Optional;

import com.departamentos.departamentos.exceptions.NotFoundException;




public class RepositoryUtils {

    private RepositoryUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static <T> T findOrThrow(Optional<T> optional, String mensajeError) {
        return optional.orElseThrow(() -> new NotFoundException(mensajeError));
    }

}
