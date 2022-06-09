package com.digitaltalent.hackaton.converter.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

class ConverterServiceImplTest {

  private ConverterServiceImpl service;

  @BeforeEach
  void setUp() {
    service = new ConverterServiceImpl();
  }

  @Test
  void converterTest() throws Exception {
    File csvFile = new File("src/test/resources/files/file-decimals.csv");
    FileInputStream inputStream = new FileInputStream(csvFile);
    MockMultipartFile file = new MockMultipartFile("file", csvFile.getName(), "text/csv",
        IOUtils.toByteArray(inputStream));

    ResponseEntity<String> response = service.converter(file, ",");

    String jsonFile = new String(Files.readAllBytes(Paths.get("src/test/resources/files/body-decimal.json")));

    assertEquals(response.getStatusCode(), HttpStatus.OK);
    assertEquals(response.getBody(), jsonFile);
    assertNotNull(response.getBody());
  }

  @Test
  void converterMultivalueTest() throws Exception {
    File csvFile = new File("src/test/resources/files/file-multivalue.csv");
    FileInputStream inputStream = new FileInputStream(csvFile);
    MockMultipartFile file = new MockMultipartFile("file", csvFile.getName(), "text/csv",
        IOUtils.toByteArray(inputStream));

    ResponseEntity<String> response = service.converter(file, ";");

    String jsonFile = new String(Files.readAllBytes(Paths.get("src/test/resources/files/body-multivalue.json")));

    assertEquals(response.getStatusCode(), HttpStatus.OK);
    assertEquals(response.getBody(), jsonFile);
    assertNotNull(response.getBody());
  }
}