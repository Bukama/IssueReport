
package com.github.bukama.ir;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import com.github.bukama.ir.jaxb.IssueReport;
import com.github.bukama.ir.jaxb.IssueType;
import com.github.bukama.ir.jaxb.TestCaseType;
import com.github.bukama.ir.listreader.IssueListReader;

import org.junitpioneer.jupiter.IssueProcessor;
import org.junitpioneer.jupiter.IssueTestCase;
import org.junitpioneer.jupiter.IssueTestSuite;

public class IssueReportProcessor implements IssueProcessor {

  private static final Logger LOG = Logger.getAnonymousLogger();

  private static final IssueListReader ISSUE_LIST_READER = new IssueListReader();

  @Override
  public void processTestResults(List<IssueTestSuite> allIssueTestsSuites) {

    IssueReport report = new IssueReport();

    // Read issue list
    List<IssueType> allIssues = ISSUE_LIST_READER.readIssues();

    // Merge list, if theres something to merge
    if (allIssueTestsSuites.isEmpty()) {
      if (!allIssues.isEmpty()) {
        report.getIssues().getIssue().addAll(allIssues);
      } else {
        LOG.info("No issues provided and no tests annotated with tests");
      }
    } else {
      report.getIssues().getIssue().addAll(mergeLists(allIssues, allIssueTestsSuites));
    }

    // TODO: Create summaries

    // TODO: Marshall it a report
  }

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
  List<IssueType> mergeLists(List<IssueType> allIssues, List<IssueTestSuite> allIssueTestsSuites) {

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
  List<TestCaseType> convertToTestCaseType(List<IssueTestCase> allTests) {
    List<TestCaseType> resultList = new ArrayList<>();

    allTests.forEach(test -> resultList.add(new TestCaseType(test.testId(), test.result().toString())));
    return resultList;
  }
}
