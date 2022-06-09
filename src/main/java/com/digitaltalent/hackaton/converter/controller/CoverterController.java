package com.digitaltalent.hackaton.converter.controller;

import com.digitaltalent.hackaton.converter.service.ConverterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("converter/api/v1")
public class CoverterController {

  private final ConverterService converterService;

  public CoverterController(ConverterService converterService) {
    this.converterService = converterService;
  }

  @PostMapping("/upload")
  public ResponseEntity<String> converter(
      @RequestParam("file")MultipartFile file,
      @RequestParam("separator") String separator) {
    return converterService.converter(file, separator);
  }

}
