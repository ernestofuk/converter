package com.digitaltalent.hackaton.converter.service.impl;

import com.digitaltalent.hackaton.converter.service.ConverterService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class ConverterServiceImpl implements ConverterService {

  public ConverterServiceImpl() {
  }

  @Override
  public ResponseEntity<String> converter(MultipartFile file, String separator) {
    try {
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()));
      List<String> columnNames = this.getNames(bufferedReader, separator);
      List<Map<String, Object>> jsonMap = this.getValues(bufferedReader, separator, columnNames);
      return new ResponseEntity<>(this.getJson(jsonMap), HttpStatus.OK);
    } catch (IOException e) {
      log.error(e.getMessage());
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  private List<String> getNames(BufferedReader bufferedReader, String separator)
      throws IOException {
    return List.of(bufferedReader.readLine().split(separator));
  }

  private List<Map<String, Object>> getValues(BufferedReader bufferedReader, String separator,
      List<String> columnNames)
      throws IOException {
    String line = StringUtils.EMPTY;
    List<Map<String, Object>> jsonMap = new ArrayList<>();
    while ((line = bufferedReader.readLine()) != null) {
      String[] values = line.split(separator);
      Map<String, Object> data = new LinkedHashMap<>();
      for (int i = 0; i < columnNames.size(); i++) {
        data.put(columnNames.get(i), this.getCorrectValue(values[i]));
      }
      jsonMap.add(data);
    }
    return jsonMap;
  }

  private String getJson(List<Map<String, Object>> jsonMap) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.enable(SerializationFeature.INDENT_OUTPUT);
    return mapper.writeValueAsString(jsonMap);
  }

  private Object getCorrectValue(String value) {
    if (NumberUtils.isParsable(value)) {
      Double number = Double.parseDouble(value);
      if (number % 1 == 0) {
        return Integer.parseInt(value);
      }
      return number;
    } else {
      return value;
    }
  }

}
