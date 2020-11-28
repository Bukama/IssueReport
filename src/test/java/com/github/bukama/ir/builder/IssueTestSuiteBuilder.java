
package com.github.bukama.ir.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junitpioneer.jupiter.IssueTestCase;
import org.junitpioneer.jupiter.IssueTestSuite;

public class IssueTestSuiteBuilder {

  public static IssueTestSuite withThreeFixedNamesTests(String issueId) {
    List<IssueTestCase> tests = IssueTestCaseBuilder.listWithThreeTestsFixedNameAndAllResults();
    return new IssueTestSuite(issueId, tests);
  }

  public static IssueTestSuite withThreeRandomNamesTests(String issueId) {
    List<IssueTestCase> tests = IssueTestCaseBuilder.listWithThreeTestsRandomNameAndAllResults();
    return new IssueTestSuite(issueId, tests);
  }

  public static List<IssueTestSuite> withRandomIssueIds(int numberOfSuites) {
    List<IssueTestSuite> suite = new ArrayList<>(numberOfSuites);

    for (int i = 1; i <= numberOfSuites; i++) {
      suite.add(new IssueTestSuite(UUID.randomUUID().toString(),
          IssueTestCaseBuilder.listWithThreeTestsRandomNameAndAllResults()));
    }

    return suite;
  }
}
