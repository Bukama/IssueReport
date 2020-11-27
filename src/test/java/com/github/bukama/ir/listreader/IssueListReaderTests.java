
package com.github.bukama.ir.listreader;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IssueListReaderTests {

  IssueListReader sut;

  @BeforeEach
  void setUp() {
    sut = new IssueListReader();
  }

  @Test
  void buildFileNameCorrect() {
    String expected = "." + File.separator + "issuelist.csv";
    String actual = sut.buildFileName();

    assertThat(actual).isEqualTo(expected);
  }
}
