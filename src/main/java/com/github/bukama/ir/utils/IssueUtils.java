
package com.github.bukama.ir.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.github.bukama.ir.jaxb.IssueType;
import com.github.bukama.ir.jaxb.TestCaseType;

import org.junitpioneer.jupiter.IssueTestCase;
import org.junitpioneer.jupiter.IssueTestSuite;

/**
 * Util class for handling issues.
 */
public class IssueUtils {

  /**
   * Merges the issues from the provided list and tests annotated with the {@link org.junitpioneer.jupiter.Issue @Issue}
   * annotation.
   *
   * @param allIssues
   *          Issues read from the provided list
   * @param allIssueTestsSuites
   *          Tests recorded by the IssueProcessor
   * @return List with merged information
   */
  public static List<IssueType> mergeLists(List<IssueType> allIssues, List<IssueTestSuite> allIssueTestsSuites) {

    ConcurrentHashMap<String, IssueType> merged = new ConcurrentHashMap<>();

    allIssues.forEach(issue -> merged.put(issue.getIssueId(), issue));

    for (IssueTestSuite testSuite : allIssueTestsSuites) {
      String issueId = testSuite.issueId();

      if (!merged.containsKey(issueId)) {
        merged.put(issueId, new IssueType(issueId));
      }

      merged.get(issueId).getTests().getTestCase().addAll(convertToTestCaseType(testSuite.tests()));
    }

    return new ArrayList<>(merged.values());
  }

  /**
   * Converts the {@link IssueTestCase}s into {@link TestCaseType}.
   *
   * @param allTests
   *          List of IssueTestCases
   * @return List of TestCaseTypes
   */
  static List<TestCaseType> convertToTestCaseType(List<IssueTestCase> allTests) {
    List<TestCaseType> resultList = new ArrayList<>();

    allTests.forEach(test -> resultList.add(new TestCaseType(test.testId(), test.result().toString())));
    return resultList;
  }
}
