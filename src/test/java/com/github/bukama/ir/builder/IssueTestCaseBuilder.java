
package com.github.bukama.ir.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.platform.engine.TestExecutionResult;
import org.junitpioneer.jupiter.IssueTestCase;

public class IssueTestCaseBuilder {

  public static List<IssueTestCase> listWithThreeTestsFixedNameAndAllResults() {
    List<IssueTestCase> tests = new ArrayList<>();
    tests.add(new IssueTestCase("test1", TestExecutionResult.Status.SUCCESSFUL));
    tests.add(new IssueTestCase("test2", TestExecutionResult.Status.FAILED));
    tests.add(new IssueTestCase("test3", TestExecutionResult.Status.ABORTED));

    return tests;
  }

  public static List<IssueTestCase> listWithThreeTestsRandomNameAndAllResults() {
    List<IssueTestCase> tests = new ArrayList<>();
    tests.add(new IssueTestCase(UUID.randomUUID().toString(), TestExecutionResult.Status.SUCCESSFUL));
    tests.add(new IssueTestCase(UUID.randomUUID().toString(), TestExecutionResult.Status.FAILED));
    tests.add(new IssueTestCase(UUID.randomUUID().toString(), TestExecutionResult.Status.ABORTED));

    return tests;
  }
}
