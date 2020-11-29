
package com.github.bukama.ir.utils;

import java.util.ArrayList;
import java.util.List;

import com.github.bukama.ir.jaxb.IssueType;
import com.github.bukama.ir.jaxb.TestCaseType;

import org.junit.platform.engine.TestExecutionResult;

public class SummaryUtils {

  public static List<IssueType> createSummaries(List<IssueType> allIssues) {

    List<IssueType> returnList = new ArrayList<>(allIssues.size());

    for (IssueType issue : allIssues) {
      int successful = 0;
      int failed = 0;
      int aborted = 0;

      for (TestCaseType test : issue.getTests().getTestCase()) {
        if (TestExecutionResult.Status.SUCCESSFUL.toString().equals(test.getResult())) {
          successful++;
        } else if (TestExecutionResult.Status.FAILED.toString().equals(test.getResult())) {
          failed++;
        } else {
          aborted++;
        }
      }
      issue.getSummary().setAborted(aborted);
      issue.getSummary().setFailed(failed);
      issue.getSummary().setSuccessful(successful);
      issue.getSummary().setTotal(issue.getTests().getTestCase().size());

      returnList.add(issue);
    }

    return returnList;
  }
}
