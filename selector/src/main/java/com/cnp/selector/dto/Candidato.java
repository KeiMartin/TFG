package com.cnp.selector.dto;

import com.opencsv.bean.CsvBindByPosition;

import lombok.Data;

@Data
public class Candidato implements Comparable<Candidato>{
	
	@CsvBindByPosition(position = 0)
	private String dni;
	
	@CsvBindByPosition(position = 1)
	private String apellidos;
	
	@CsvBindByPosition(position = 2)
	private String nombre;
	
	@CsvBindByPosition(position = 7)
	private String plantilla;
	
	@CsvBindByPosition(position = 13)
	private int valoracion; 
	
	@CsvBindByPosition(position = 14)
	private int prioridad;
	
	@Override
	public int compareTo(Candidato o) {
		// TODO Auto-generated method stub
		//primero se compara por valoración
		int resultado = Integer.compare(o.getValoracion(), this.getValoracion());
		if (resultado == 0)
			resultado = Integer.compare(this.getPrioridad(), o.getPrioridad());
		return resultado;
	}
	

}
