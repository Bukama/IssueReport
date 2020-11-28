
package com.github.bukama.ir;

import java.util.List;
import java.util.logging.Logger;

import com.github.bukama.ir.jaxb.IssueReport;
import com.github.bukama.ir.jaxb.IssueType;
import com.github.bukama.ir.listreader.IssueListReader;
import com.github.bukama.ir.utils.IssueUtils;
import com.github.bukama.ir.utils.SummaryUtils;

import org.junitpioneer.jupiter.IssueProcessor;
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
    allIssues = IssueUtils.mergeLists(allIssues, allIssueTestsSuites);

    // TODO: Create summaries
    allIssues = SummaryUtils.createSummaries(allIssues);
    // TODO: Marshall it a report
  }

}
