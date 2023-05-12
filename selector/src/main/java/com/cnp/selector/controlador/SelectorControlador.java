package com.cnp.selector.controlador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cnp.selector.dto.Candidato;

@RestController
public class SelectorControlador {

	//voy a hacer el endpoint
	@PostMapping(path="/seleccionados")
	public List<Candidato> candidatoSeleccionados(@RequestPart MultipartFile csv, @RequestParam("plazas") Integer plazas) {
		return new ArrayList<Candidato>();
	}
}
