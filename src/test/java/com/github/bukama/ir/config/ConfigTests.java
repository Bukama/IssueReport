
package com.github.bukama.ir.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.SetSystemProperty;

public class ConfigTests {

  @Test
  void readDefaultValue() {
    String expected = "csv";
    String actual = Config.ISSUELIST_EXTENSION.value();

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  @SetSystemProperty(key = "com.github.bukama.ir.issuelist.extension", value = "xml")
  void readProvidedValue() {
    String expected = "xml";
    String actual = Config.ISSUELIST_EXTENSION.value();

    assertThat(actual).isEqualTo(expected);
  }
}
