package com.departamentos.departamentos.services;

import com.departamentos.departamentos.dto.SearchDepartamentoResponse;

public interface SearchDepartamentoService {

    SearchDepartamentoResponse findByNombreDepto(String nombreDepto, int pageNumber);

}
