package com.departamentos.departamentos.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.departamentos.departamentos.entities.Departamento;
import com.departamentos.departamentos.entities.Departamento.NivelDepartamento;

import java.util.List;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

    Page<Departamento> findByNombreDepartamentoContainingIgnoreCase(String nombreDepto, Pageable pageable);

    List<Departamento> findByNivel(NivelDepartamento nivel);

}
