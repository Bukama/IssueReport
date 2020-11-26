package com.github.bukama.ir;

import org.junitpioneer.jupiter.IssueProcessor;
import org.junitpioneer.jupiter.IssueTestSuite;

import java.util.List;

public class IssueReportProcessor implements IssueProcessor {

	@Override public void processTestResults(List<IssueTestSuite> list) {
		for(IssueTestSuite suite : list) {
			System.out.println("Suite-id:" + suite.issueId());
		}
	}
}
