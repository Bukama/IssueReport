
package com.github.bukama.ir.listreader;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.SetSystemProperty;

public class SupportedListTypesTests {

  @Test
  @SetSystemProperty(key = "com.github.bukama.ir.issuelist.extension", value = "csv")
  void supportedTypeLowercase() {
    SupportedListTypes expected = SupportedListTypes.CSV;
    SupportedListTypes actual = SupportedListTypes.byConfig();

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  @SetSystemProperty(key = "com.github.bukama.ir.issuelist.extension", value = "CSV")
  void supportedTypeUppercase() {
    SupportedListTypes expected = SupportedListTypes.CSV;
    SupportedListTypes actual = SupportedListTypes.byConfig();

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  @SetSystemProperty(key = "com.github.bukama.ir.issuelist.extension", value = " CSV ")
  void supportedTypeTrimmed() {
    SupportedListTypes expected = SupportedListTypes.CSV;
    SupportedListTypes actual = SupportedListTypes.byConfig();

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  @SetSystemProperty(key = "com.github.bukama.ir.issuelist.extension", value = "NotSupported")
  void notSupportedType() {
    assertThatThrownBy(SupportedListTypes::byConfig).isInstanceOf(IllegalArgumentException.class)
        .hasMessage("No enum constant com.github.bukama.ir.listreader.SupportedListTypes.NOTSUPPORTED");

  }
}
