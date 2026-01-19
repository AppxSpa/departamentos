package com.departamentos.departamentos.services;

import org.springframework.stereotype.Service;

import com.departamentos.departamentos.dto.CargoFunc;
import com.departamentos.departamentos.entities.Departamento;
import com.departamentos.departamentos.repositories.DepartamentoRepository;
import com.departamentos.departamentos.utils.RepositoryUtils;

@Service
public class EsJefeServiceImpl implements EsJefeService {

    private final DepartamentoRepository departamentoRepository;

    public EsJefeServiceImpl(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }

    @Override
    public CargoFunc esjefe(Long idDepto, Integer rut) {

        Departamento departamento = RepositoryUtils.findOrThrow(departamentoRepository.findById(idDepto),
                String.format("Departamento con id %d no encontrado", idDepto));

        boolean esJefe = departamento.getRutJefe().equals(rut);

        boolean esDirector = nivelDireccion(departamento.getNivel()) && departamento.getRutJefe().equals(rut);

        return new CargoFunc(esJefe, esDirector);

    }

    private boolean nivelDireccion(Departamento.NivelDepartamento nivel) {
        return switch (nivel) {

            case DIRECCION, ADMINISTRACION, ALCALDIA -> true;
            default -> false;
        };
    }

}
