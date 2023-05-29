package com.cnp.selector.servicios.impl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cnp.selector.dto.Candidato;
import com.cnp.selector.servicios.CsvServicio;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

@Service
public class CsvServicioImpl implements CsvServicio {
	public List<Candidato> convertirCsv(MultipartFile archivo) throws IOException {
		Reader lector = new InputStreamReader(archivo.getInputStream());
		CSVReader lectorCsv = new CSVReaderBuilder(lector)//leo fichero mediante openCsv=estructura del fichero
				.withSkipLines(1)//salto las cabeceras
				.build();
		CsvToBean<Candidato> csvToCandidato = new CsvToBeanBuilder<Candidato>(lectorCsv) //openCsv=cada fila es un objeto candidato
				.withType(Candidato.class)
                .build();
		return csvToCandidato.parse();
		
	}

}
