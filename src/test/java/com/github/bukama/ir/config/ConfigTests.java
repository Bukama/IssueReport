
package com.github.bukama.ir.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.SetSystemProperty;

public class ConfigTests {

  @Test
  void readDefaultStringValue() {
    String expected = "csv";
    String actual = Config.ISSUELIST_EXTENSION.asString();

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  @SetSystemProperty(key = "com.github.bukama.ir.issuelist.extension", value = "xml")
  void readProvidedStringValue() {
    String expected = "xml";
    String actual = Config.ISSUELIST_EXTENSION.asString();

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  @SetSystemProperty(key = "com.github.bukama.ir.issuelist.csv.skipfirstline", value = "true")
  void readBooleanValue() {
    boolean actual = Config.ISSUELIST_CSV_SKIPFIRSTLINE.asBoolean();

    assertThat(actual).isTrue();
  }
}
