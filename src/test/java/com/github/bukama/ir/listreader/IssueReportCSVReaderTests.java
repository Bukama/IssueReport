
package com.github.bukama.ir.listreader;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.ArrayList;
import java.util.List;

import com.github.bukama.ir.jaxb.IssueType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.SetSystemProperty;

@SetSystemProperty(key = "com.github.bukama.ir.issuelist.directory", value = "./examples")
public class IssueReportCSVReaderTests {

  IssueReportCSVReader sut;
  IssueListReader issueListReader = new IssueListReader();

  @BeforeEach
  void setUp() {
    sut = new IssueReportCSVReader();
  }

  @Test
  @SetSystemProperty(key = "com.github.bukama.ir.issuelist.filename", value = "oneLine")
  @SetSystemProperty(key = "com.github.bukama.ir.issuelist.csv.skipfirstline", value = "false")
  void processFileWithOneLine() {
    IssueType expected = new IssueType("req-123", "description with spaces", "HIGH");

    String fileName = issueListReader.buildFileName();

    List<IssueType> actual = new ArrayList<>(sut.readFile(fileName));

    assertAll(() -> assertThat(actual.size()).isEqualTo(1), () -> assertThat(actual.get(0)).isEqualTo(expected));
  }

  @Test
  @SetSystemProperty(key = "com.github.bukama.ir.issuelist.filename", value = "threeLines")
  @SetSystemProperty(key = "com.github.bukama.ir.issuelist.csv.skipfirstline", value = "false")
  void processFileWithThreeLines() {
    List<IssueType> expectedIssues = new ArrayList<>();
    expectedIssues.add(new IssueType("req-123", "description with spaces", "HIGH"));
    expectedIssues.add(new IssueType("req-222", "second", "2"));
    expectedIssues.add(new IssueType("req-333", "third", "LOW"));

    String fileName = issueListReader.buildFileName();

    List<IssueType> actual = new ArrayList<>(sut.readFile(fileName));

    assertAll(() -> assertThat(actual.size()).isEqualTo(3),
        () -> assertThat(actual).containsExactlyInAnyOrderElementsOf(expectedIssues));
  }

  @Test
  @SetSystemProperty(key = "com.github.bukama.ir.issuelist.filename", value = "oneLine")
  @SetSystemProperty(key = "com.github.bukama.ir.issuelist.csv.skipfirstline", value = "true")
  void processLineSkipFirstLine() {
    String fileName = issueListReader.buildFileName();
    List<IssueType> actual = new ArrayList<>(sut.readFile(fileName));

    assertThat(actual.size()).isEqualTo(0);
  }

  @Test
  @SetSystemProperty(key = "com.github.bukama.ir.issuelist.filename", value = "tooMuchElements")
  void tooMuchElementsInLine() {
    String fileName = issueListReader.buildFileName();

    assertThatThrownBy(() -> sut.readFile(fileName)).isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Line does not contain three elements, but [4]");
  }

  @Test
  @SetSystemProperty(key = "com.github.bukama.ir.issuelist.filename", value = "tooLessElements")
  void tooLessElementsInLine() {
    String fileName = issueListReader.buildFileName();

    assertThatThrownBy(() -> sut.readFile(fileName)).isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Line does not contain three elements, but [2]");
  }

  @Test
  @SetSystemProperty(key = "com.github.bukama.ir.issuelist.filename", value = "nonexisting")
  void FileNotFound() {
    String fileName = issueListReader.buildFileName();

    List<IssueType> actual = new ArrayList<>(sut.readFile(fileName));
    assertThat(actual.size()).isEqualTo(0);
  }
}
