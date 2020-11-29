
package com.github.bukama.ir.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.ArrayList;
import java.util.List;

import com.github.bukama.ir.builder.IssueTestCaseBuilder;
import com.github.bukama.ir.jaxb.IssueType;
import com.github.bukama.ir.jaxb.TestCaseType;

import org.junit.jupiter.api.Test;
import org.junit.platform.engine.TestExecutionResult;
import org.junitpioneer.jupiter.IssueTestCase;
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
    // Prepare data
    List<IssueTestCase> testCases = new ArrayList<>();
    testCases.addAll(IssueTestCaseBuilder.listWithResult(3, TestExecutionResult.Status.SUCCESSFUL));
    testCases.addAll(IssueTestCaseBuilder.listWithResult(1, TestExecutionResult.Status.ABORTED));
    testCases.addAll(IssueTestCaseBuilder.listWithResult(6, TestExecutionResult.Status.FAILED));

    List<IssueTestSuite> testSuites = new ArrayList<>();
    testSuites.add(new IssueTestSuite("req-123", testCases));
    testSuites.add(
        new IssueTestSuite("req-222", IssueTestCaseBuilder.listWithResult(4, TestExecutionResult.Status.SUCCESSFUL)));

    List<IssueType> allIssues = IssueUtils.mergeLists(new ArrayList<>(), testSuites);

    // Execute test
    List<IssueType> actual = SummaryUtils.createSummaries(allIssues);

    assertAll(() -> assertThat(actual.size()).isEqualTo(2),
        () -> assertThat(actual.get(0).getIssueId()).isEqualTo("req-123"),
        () -> assertThat(actual.get(0).getSummary().getTotal()).isEqualTo(10),
        () -> assertThat(actual.get(0).getSummary().getSuccessful()).isEqualTo(3),
        () -> assertThat(actual.get(0).getSummary().getFailed()).isEqualTo(6),
        () -> assertThat(actual.get(0).getSummary().getAborted()).isEqualTo(1),
        () -> assertThat(actual.get(1).getSummary().getTotal()).isEqualTo(4),
        () -> assertThat(actual.get(1).getSummary().getSuccessful()).isEqualTo(4),
        () -> assertThat(actual.get(1).getSummary().getFailed()).isEqualTo(0),
        () -> assertThat(actual.get(1).getSummary().getAborted()).isEqualTo(0));
  }

  @Test
  void updateSummarNoTests() {
    // Prepare data
    List<IssueTestSuite> testSuites = new ArrayList<>();
    testSuites.add(new IssueTestSuite("req-123", new ArrayList<>()));

    List<IssueType> allIssues = IssueUtils.mergeLists(new ArrayList<>(), testSuites);

    // Execute test
    List<IssueType> actual = SummaryUtils.createSummaries(allIssues);

    assertAll(() -> assertThat(actual.size()).isEqualTo(1),
        () -> assertThat(actual.get(0).getIssueId()).isEqualTo("req-123"),
        () -> assertThat(actual.get(0).getSummary().getTotal()).isEqualTo(0),
        () -> assertThat(actual.get(0).getSummary().getSuccessful()).isEqualTo(0),
        () -> assertThat(actual.get(0).getSummary().getFailed()).isEqualTo(0),
        () -> assertThat(actual.get(0).getSummary().getAborted()).isEqualTo(0));
  }
}
