package com.cnp.selector.servicios.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnp.selector.dto.Candidato;
import com.cnp.selector.servicios.FiltrarServicio;

public class FiltrarServicioImpl implements FiltrarServicio {

	public List<Candidato> seleccionar(List<Candidato> candidatos, Integer plazas) {
		return null;
	}
		
	private List<Candidato> unificarPlantillas (List<Candidato> candidatos, Integer plazas){
		
		return null;
	}
	
	private Map<String, Double> calcularRatio (List <Candidato> candidatos, Integer plazas){
		HashMap<String, Integer> mapaCandidatoPlantilla = new HashMap<>();//mapa tipo hash porque no permite duplicados
		for (Candidato c:candidatos) {
			String plantilla = c.getPlantilla();
			if (!mapaCandidatoPlantilla.containsKey(plantilla)) {
				mapaCandidatoPlantilla.put(plantilla, 0);
			}
			mapaCandidatoPlantilla.put(plantilla, mapaCandidatoPlantilla.get(plantilla)+1);
		}
		Integer totalCandidatos = candidatos.size();//total de preinscritos
		Double plazasPorCandidato = (plazas / (double) totalCandidatos); //ratio general
		HashMap<String, Double> mapaRatio = new HashMap<>();
		for (String plantilla:mapaCandidatoPlantilla.keySet()) {
		return null;
		}
	}
}
