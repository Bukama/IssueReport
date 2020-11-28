
package com.github.bukama.ir.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.ArrayList;
import java.util.List;

import com.github.bukama.ir.builder.IssueTestSuiteBuilder;
import com.github.bukama.ir.jaxb.IssueType;
import com.github.bukama.ir.jaxb.TestCaseType;

import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.IssueTestSuite;

public class SummaryUtilsTests {

  static final TestCaseType TEST1 = new TestCaseType("test1", "SUCCESSFUL");
  static final IssueType ISSUE1 = new IssueType("req-123", "description", "1");
  static final IssueType ISSUE2 = new IssueType("req-222", "another one", "2");

  @Test
  void mergeListEmptyList() {
    List<IssueType> actual = SummaryUtils.createSummaries(new ArrayList<>());
    assertThat(actual.size()).isEqualTo(0);
  }

  @Test
  void updateSummaryCorrectly() {
    assertThat(true).isEqualTo(false);
    List<IssueType> issues = new ArrayList<>();
    issues.add(ISSUE1);
    issues.add(ISSUE2);

    List<IssueTestSuite> testSuites = new ArrayList<>();
    testSuites.add(IssueTestSuiteBuilder.withThreeFixedNamesTests("req-123"));

    List<IssueType> actual = IssueUtils.mergeLists(issues, testSuites);

    assertAll(() -> assertThat(actual.size()).isEqualTo(2),
        () -> assertThat(actual.get(0).getIssueId()).isEqualTo("req-123"),
        () -> assertThat(actual.get(0).getTests().getTestCase().size()).isEqualTo(3),
        () -> assertThat(actual.get(0).getTests().getTestCase().get(0).getTestId()).isEqualTo("test1"),
        () -> assertThat(actual.get(1)).isEqualTo(ISSUE2));
  }
}
