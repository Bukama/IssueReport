
package com.github.bukama.ir;

import java.util.List;

import org.junitpioneer.jupiter.IssueProcessor;
import org.junitpioneer.jupiter.IssueTestSuite;

public class IssueReportProcessor implements IssueProcessor {

  @Override
  public void processTestResults(List<IssueTestSuite> list) {
    for (IssueTestSuite suite : list) {
      System.out.println("Suite-id:" + suite.issueId());
    }
  }
}
