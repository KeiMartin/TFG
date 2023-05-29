package com.cnp.selector.servicios;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.cnp.selector.dto.Candidato;

public interface CsvServicio {
	public List<Candidato> convertirCsv(MultipartFile csv) throws IOException;
}
