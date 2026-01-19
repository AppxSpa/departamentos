package com.departamentos.departamentos.services;

import java.util.List;

import com.departamentos.departamentos.dto.DepartamentoList;
import com.departamentos.departamentos.dto.DepartamentoResponse;

public interface DepartamentoService {

    DepartamentoResponse getDeparamentoByCodigoExt(String codEx);

    DepartamentoResponse getDepartamentoById(Long id);

    List<DepartamentoList> getDepartamentosList();

}
