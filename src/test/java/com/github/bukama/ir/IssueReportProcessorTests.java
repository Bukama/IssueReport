
package com.github.bukama.ir;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.ArrayList;
import java.util.List;

import com.github.bukama.ir.builder.IssueTestCaseBuilder;
import com.github.bukama.ir.jaxb.TestCaseType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.IssueTestCase;

public class IssueReportProcessorTests {

  IssueReportProcessor sut;

  @BeforeEach
  void setUp() {
    sut = new IssueReportProcessor();
  }

  @Test
  void convertToTestCaseTypeCorrectly() {
    TestCaseType expectedTest = new TestCaseType("test1", "SUCCESSFUL");

    List<IssueTestCase> testCases = IssueTestCaseBuilder.listWithThreeTestsFixedNameAndAllResults();
    List<TestCaseType> actual = sut.convertToTestCaseType(testCases);
    assertAll(() -> assertThat(actual.size()).isEqualTo(3), () -> assertThat(actual.get(0)).isEqualTo(expectedTest));
  }

  @Test
  void convertToTestCaseTypeEmtyList() {
    List<TestCaseType> actual = sut.convertToTestCaseType(new ArrayList<>());
    assertThat(actual.size()).isEqualTo(0);
  }
}
