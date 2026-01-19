package com.departamentos.departamentos.services;

import org.springframework.stereotype.Service;
import com.departamentos.departamentos.dto.DepartamentoJerarquiaDTO;
import com.departamentos.departamentos.entities.Departamento;
import com.departamentos.departamentos.entities.Departamento.NivelDepartamento;
import com.departamentos.departamentos.exceptions.NotFoundException;
import com.departamentos.departamentos.repositories.DepartamentoRepository;

import java.util.Collections;
import java.util.List;

@Service
public class JerarquiaServiceImpl implements JerarquiaService {

    private final DepartamentoRepository departamentoRepository;

    public JerarquiaServiceImpl(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }

    @Override
    public DepartamentoJerarquiaDTO getJerarquiaPorId(Long id) {
        Departamento departamento = departamentoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No se encontró el departamento con id: " + id));

        if (departamento.getNivel() == NivelDepartamento.ALCALDIA) {
            List<Departamento> direcciones = departamentoRepository.findByNivel(NivelDepartamento.DIRECCION);
            List<DepartamentoJerarquiaDTO> direccionDTOs = direcciones.stream()
                    .map(dir -> new DepartamentoJerarquiaDTO(dir.getId(), dir.getNombreDepartamento(),
                            dir.getNivel().toString(), Collections.emptyList()))
                    .toList();

            List<Departamento> administraciones = departamentoRepository.findByNivel(NivelDepartamento.ADMINISTRACION);
            List<DepartamentoJerarquiaDTO> administracionDTOs = administraciones.stream()
                    .map(admin -> new DepartamentoJerarquiaDTO(admin.getId(), admin.getNombreDepartamento(),
                            admin.getNivel().toString(), direccionDTOs))
                    .toList();

            return new DepartamentoJerarquiaDTO(departamento.getId(), departamento.getNombreDepartamento(),
                    departamento.getNivel().toString(), administracionDTOs);

        } else if (departamento.getNivel() == NivelDepartamento.ADMINISTRACION) {
            List<Departamento> direcciones = departamentoRepository.findByNivel(NivelDepartamento.DIRECCION);
            List<DepartamentoJerarquiaDTO> hijos = direcciones.stream()
                    .map(dir -> new DepartamentoJerarquiaDTO(dir.getId(), dir.getNombreDepartamento(),
                            dir.getNivel().toString(), Collections.emptyList()))
                    .toList();
            return new DepartamentoJerarquiaDTO(departamento.getId(), departamento.getNombreDepartamento(),
                    departamento.getNivel().toString(), hijos);
        }

        return construirJerarquia(departamento);
    }

    private DepartamentoJerarquiaDTO construirJerarquia(Departamento departamento) {
        List<DepartamentoJerarquiaDTO> hijos = departamento.getChildrens().stream()
                .map(this::construirJerarquia)
                .toList();
        return new DepartamentoJerarquiaDTO(departamento.getId(), departamento.getNombreDepartamento(),
                departamento.getNivel().toString(), hijos);
    }
}
