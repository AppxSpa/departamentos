package com.departamentos.departamentos.services;

import com.departamentos.departamentos.dto.DepartamentoResponse;
import java.util.List;

public interface FamiliaDepartamentoService {

    List<DepartamentoResponse> getFamilia(Long id);

}
