
package com.github.bukama.ir.builder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.platform.engine.TestExecutionResult;
import org.junitpioneer.jupiter.IssueTestCase;

public class IssueTestCaseBuilderTests {

  @Test
  void listWithThreeTestsFixedNameAndAllResultsTest() {
    List<IssueTestCase> expected = new ArrayList<>();
    expected.add(new IssueTestCase("test1", TestExecutionResult.Status.SUCCESSFUL));
    expected.add(new IssueTestCase("test2", TestExecutionResult.Status.FAILED));
    expected.add(new IssueTestCase("test3", TestExecutionResult.Status.ABORTED));

    List<IssueTestCase> actual = IssueTestCaseBuilder.listWithThreeTestsFixedNameAndAllResults();

    assertThat(actual).isEqualTo(expected);

  }

  @Test
  void listWithThreeTestsRandomNameAndAllResultsTest() {
    List<IssueTestCase> actual = IssueTestCaseBuilder.listWithThreeTestsRandomNameAndAllResults();

    assertAll(() -> assertThat(actual.size()).isEqualTo(3),
        () -> assertThat(actual.get(0).result()).isEqualTo(TestExecutionResult.Status.SUCCESSFUL),
        () -> assertThat(actual.get(1).result()).isEqualTo(TestExecutionResult.Status.FAILED),
        () -> assertThat(actual.get(2).result()).isEqualTo(TestExecutionResult.Status.ABORTED));

    ;
  }
}
