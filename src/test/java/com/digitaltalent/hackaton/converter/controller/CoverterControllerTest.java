package com.digitaltalent.hackaton.converter.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@WebAppConfiguration
class CoverterControllerTest {

  @Autowired
  private WebApplicationContext webApplicationContext;

  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
  }

  @Test
  void converterTest() throws Exception {
    File csvFile = new File("src/test/resources/files/file-decimals.csv");
    FileInputStream inputStream = new FileInputStream(csvFile);
    MockMultipartFile file = new MockMultipartFile("file", csvFile.getName(), "text/csv",
        IOUtils.toByteArray(inputStream));

    String jsonFile = new String(Files.readAllBytes(Paths.get("src/test/resources/files/body-decimal.json")));

    this.mockMvc.perform(multipart("/converter/api/v1/upload").file(file)
            .param("separator",","))
        .andExpect(status().isOk())
        .andExpect(content().json(jsonFile))
        .andExpect(jsonPath("$", hasSize(4)));
  }

  @Test
  void converterMultivalueTest() throws Exception {
    File csvFile = new File("src/test/resources/files/file-multivalue.csv");
    FileInputStream inputStream = new FileInputStream(csvFile);
    MockMultipartFile file = new MockMultipartFile("file", csvFile.getName(), "text/csv",
        IOUtils.toByteArray(inputStream));

    String jsonFile = new String(Files.readAllBytes(Paths.get("src/test/resources/files/body-multivalue.json")));

    this.mockMvc.perform(multipart("/converter/api/v1/upload").file(file)
            .param("separator",";"))
        .andExpect(status().isOk())
        .andExpect(content().json(jsonFile))
        .andExpect(jsonPath("$", hasSize(3)));
  }
}