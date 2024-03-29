package com.cnp.selector.servicios.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cnp.selector.dto.Candidato;
import com.cnp.selector.servicios.FiltrarServicio;
@Service
public class FiltrarServicioImpl implements FiltrarServicio {

	public List<Candidato> seleccionar(List<Candidato> candidatos, Integer plazas) {
		List<Candidato> candidatosUnificados = unificarPlantillas(candidatos, plazas);//lista sin menores
		Collections.sort(candidatosUnificados);//ordenada por valoracion y prioridad
		return obtenerSeleccionados(candidatosUnificados, plazas);//lista cortada en n plazas
	}
		
	private List<Candidato> unificarPlantillas(List<Candidato> candidatos, Integer plazas){
		Map<String, Double> mapaRatio = calcularRatio(candidatos, plazas);
		HashMap<String, Double> mapaPlantillasMenores = new HashMap<>();// Unificamos las plantillas con ratio <= 1 en una nueva plantilla 'MIXTA'
		for (String plantilla:mapaRatio.keySet()) { 
			double ratio = mapaRatio.get(plantilla);
			if (ratio <=1) {
				mapaPlantillasMenores.put(plantilla, ratio);
			}
		}
		// Reemplazamos la plantilla de todos los candidatos que pertenecen a una plantilla con ratio <= 1 por MIXTA
		for (Candidato c:candidatos) {
			String plantilla = c.getPlantilla();
			if (mapaPlantillasMenores.containsKey(plantilla)) {
				c.setPlantilla("MIXTA");
			}
		}
		return candidatos;
	}
	
	private Map<String, Double> calcularRatio(List<Candidato> candidatos, Integer plazas){
		HashMap<String, Integer> mapaCandidatoPlantilla = new HashMap<>();
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
			Integer candidatosPlantilla = mapaCandidatoPlantilla.get(plantilla);
			double ratio = plazasPorCandidato*candidatosPlantilla;
			mapaRatio.put(plantilla, redondearAlza(ratio));
		}
		return mapaRatio;
	}
	private double redondearAlza(double source) {//método para redondear al alza con dos decimales
		BigDecimal bd = BigDecimal.valueOf(source);
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}
	
	private List<Candidato> obtenerSeleccionados(List<Candidato> candidatosUnificados, Integer plazas){
		Map<String, Double> mapaRatioFin = calcularRatio(candidatosUnificados, plazas);
		List<Candidato> candidatosSeleccionados = new ArrayList<>();
		for(Candidato c: candidatosUnificados){
			var plantilla = c.getPlantilla();
			var plazasLibresPlantilla = mapaRatioFin.get(plantilla);
			var plazasAdjudicadas = candidatosSeleccionados.size();
			if (plazasAdjudicadas < plazas && plazasLibresPlantilla > 0.5) {
				candidatosSeleccionados .add(c);
				mapaRatioFin.put(plantilla, mapaRatioFin.get(plantilla) - 1);
			}
		} 
		return candidatosSeleccionados;
	}
}
