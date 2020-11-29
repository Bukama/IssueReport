
package com.github.bukama.ir;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;

import com.github.bukama.ir.config.Config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IssueReportProcessorTests {

  IssueReportProcessor sut;

  @BeforeEach
  void setUp() {
    sut = new IssueReportProcessor();
  }

  @Test
  void buildFileNameCorrect() {
    String expected = "." + File.separator + Config.REPORT_DIRECTORY.asString() + File.separator + "issueReport.xml";
    String actual = sut.buildReportFileName();

    assertThat(actual).isEqualTo(expected);
  }

}
