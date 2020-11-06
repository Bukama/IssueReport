package com.github.bukama.ir;

import org.junitpioneer.jupiter.issue.IssueProcessor;
import org.junitpioneer.jupiter.issue.IssuedTestCase;

import java.util.List;

public class IssueReportProcessor implements IssueProcessor {

	@Override public void processTestResults(List<IssuedTestCase> list) {

		System.out.println("why nothing is processed?");

		for(IssuedTestCase test : list) {
			System.out.println(test.getIssueId());
		}
	}
}
