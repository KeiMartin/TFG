package com.cnp.selector.controlador;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cnp.selector.dto.Candidato;
import com.cnp.selector.servicios.CsvServicio;

@RestController
public class SelectorControlador {

	@Autowired
	CsvServicio csvServicio;
	
	//voy a hacer el endpoint
	@PostMapping(path="/seleccionados")
	public List<Candidato> candidatoSeleccionados(@RequestPart MultipartFile csv, @RequestParam("plazas") Integer plazas) throws IOException {
		return csvServicio.convertirCsv(csv);
		
	}
}
