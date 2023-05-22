package com.cnp.selector.servicios;

import java.util.List;

import com.cnp.selector.dto.Candidato;

public interface FiltrarServicio {

	public List<Candidato> seleccionar(List<Candidato> candidatos, Integer plazas);
}
