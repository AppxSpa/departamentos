package com.departamentos.departamentos.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.departamentos.departamentos.dto.DepartamentoResponse;
import com.departamentos.departamentos.entities.Departamento;
import com.departamentos.departamentos.entities.Departamento.NivelDepartamento;
import com.departamentos.departamentos.exceptions.NotFoundException;
import com.departamentos.departamentos.repositories.DepartamentoRepository;

@Service
public class FamiliaDepartamentoServiceImpl implements FamiliaDepartamentoService {

    private final DepartamentoRepository departamentoRepository;

    public FamiliaDepartamentoServiceImpl(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }

    @Override
    public List<DepartamentoResponse> getFamilia(Long id) {
        Departamento departamento = departamentoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No se encontro el departamento con id: " + id));

        List<Departamento> familia = new ArrayList<>();
        NivelDepartamento nivel = departamento.getNivel();

        switch (nivel) {
            case DIRECCION:
                getAllDescendants(departamento, familia);
                break;
            case DEPARTAMENTO, SECCION, OFICINA:
                Departamento direccionPadre = findParentDireccion(departamento);
                Departamento raiz = (direccionPadre != null) ? direccionPadre : getRaiz(departamento);
                getAllDescendants(raiz, familia);
                break;
            default:
                // Para ALCALDIA, ADMINISTRACION u otros.
                Departamento raizDefault = getRaiz(departamento);
                getAllDescendants(raizDefault, familia);
                break;
        }

        return familia.stream().map(this::toResponse).toList();
    }

    private Departamento getRaiz(Departamento departamento) {
        if (departamento.getDepartamentoSuperior() == null) {
            return departamento;
        }
        return getRaiz(departamento.getDepartamentoSuperior());
    }

    private Departamento findParentDireccion(Departamento departamento) {
        if (departamento == null || departamento.getNivel() == NivelDepartamento.DIRECCION) {
            return departamento;
        }
        return findParentDireccion(departamento.getDepartamentoSuperior());
    }

    private void getAllDescendants(Departamento departamento, List<Departamento> familia) {
        familia.add(departamento);
        for (Departamento hijo : departamento.getChildrens()) {
            getAllDescendants(hijo, familia);
        }
    }

    private DepartamentoResponse toResponse(Departamento departamento) {
        return new DepartamentoResponse(
                departamento.getId(),
                departamento.getNombreDepartamento(),
                departamento.getRutJefe(),
                departamento.getRutJefeSuperior(),
                departamento.getIdDepartamentoSuperior(),
                departamento.getNivel() != null ? departamento.getNivel().toString() : null);
    }
}