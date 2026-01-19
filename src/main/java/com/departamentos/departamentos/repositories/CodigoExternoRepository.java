package com.departamentos.departamentos.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.departamentos.departamentos.entities.CodigoExterno;

public interface CodigoExternoRepository extends JpaRepository<CodigoExterno, Long> {

    Optional<CodigoExterno> findByCodigoEx(String codigoEx);

}
