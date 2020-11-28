
package com.github.bukama.ir.builder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.IssueTestSuite;

public class IssueTestSuiteBuilderTests {

  @Test
  void withThreeFixedNamesTests() {
    IssueTestSuite expected = new IssueTestSuite("req-123",
        IssueTestCaseBuilder.listWithThreeTestsFixedNameAndAllResults());

    IssueTestSuite actual = IssueTestSuiteBuilder.withThreeFixedNamesTests("req-123");
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void withThreeRandomNamesTests() {
    IssueTestSuite actual = IssueTestSuiteBuilder.withThreeRandomNamesTests("req-123");
    assertAll(() -> assertThat(actual.tests().size()).isEqualTo(3),
        () -> assertThat(actual.issueId()).isEqualTo("req-123"));

  }

  @Test
  void withRandomIssueIdsTest() {
    List<IssueTestSuite> actual = IssueTestSuiteBuilder.withRandomIssueIds(2);

    assertAll(() -> assertThat(actual.size()).isEqualTo(2), () -> assertThat(actual.get(0).tests().size()).isEqualTo(3),
        () -> assertThat(actual.get(1).tests().size()).isEqualTo(3));

  }
}
