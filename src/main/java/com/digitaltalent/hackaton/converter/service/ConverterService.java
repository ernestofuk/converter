package com.digitaltalent.hackaton.converter.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface ConverterService {

  public ResponseEntity<String> converter(MultipartFile file, String separator);

}
