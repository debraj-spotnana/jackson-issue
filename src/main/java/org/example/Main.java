package org.example;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {

  public static void main(String[] args) {
    var str = """
      {"id" : "test-rest"}
    """;

    var objectMapper = new BaseObjectMapper();
    try {
      System.out.println(objectMapper.readValue(str, TestResp.class));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  record TestResp(String id) {}

  private static class BaseObjectMapper extends ObjectMapper {
    BaseObjectMapper() {
      setVisibility(
          getSerializationConfig()
              .getDefaultVisibilityChecker()
              .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
              .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
              .withIsGetterVisibility(JsonAutoDetect.Visibility.NONE)
              .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
              .withCreatorVisibility(JsonAutoDetect.Visibility.NONE))
          .setSerializationInclusion(Include.NON_NULL);
    }
  }
}