
package com.github.bukama.ir;

import java.util.List;

import com.github.bukama.ir.jaxb.IssueType;
import com.github.bukama.ir.listreader.IssueListReader;

import org.junitpioneer.jupiter.IssueProcessor;
import org.junitpioneer.jupiter.IssueTestSuite;

public class IssueReportProcessor implements IssueProcessor {

  private static final IssueListReader ISSUE_LIST_READER = new IssueListReader();

  @Override
  public void processTestResults(List<IssueTestSuite> allIssueTestsSuites) {

    // Read issue list
    List<IssueType> allIssues = ISSUE_LIST_READER.readIssues();

    if (allIssues.isEmpty() && allIssueTestsSuites.isEmpty()) {
      return;
    }

    // Read test list

    for (IssueTestSuite suite : allIssueTestsSuites) {
      System.out.println("Suite-id:" + suite.issueId());
    }

    // if at least one list exist, build report
  }
}
