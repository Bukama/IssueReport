
package com.github.bukama.ir.listreader;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.logging.Logger;

import com.github.bukama.ir.config.Config;
import com.github.bukama.ir.jaxb.IssueType;

/**
 * Reader to retrieve a list of issues.
 */
public class IssueListReader {

  private static Logger LOG = Logger.getAnonymousLogger();

  private Set<IssueType> issues = new HashSet<>();

  public List<IssueType> readIssues() {

    IssueReaderProvider reader;

    // Switch by purpose for further types
    switch (SupportedListTypes.byConfig()) {
      case CSV:
      default:
        reader = new IssueReportCSVReader();
        break;
    }
    String fileName = buildFileName();
    issues = reader.readFile(fileName);

    return new ArrayList<>(issues);
  }

  /**
   * Creates the full file name of the issue list based on the system properties.
   *
   * @return Full file name
   */
  String buildFileName() {
    String subDir = (!Config.ISSUELIST_DIRECTORY.asString().trim().isEmpty())
        ? Config.ISSUELIST_DIRECTORY.asString() + File.separator
        : "";

    return "." + File.separator + subDir + Config.ISSUELIST_FILENAME.asString() + "."
        + Config.ISSUELIST_EXTENSION.asString().toLowerCase(Locale.ROOT);
  }

}
