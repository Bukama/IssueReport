
package com.github.bukama.ir.listreader;

import java.io.File;
import java.util.HashSet;
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

  Set<IssueType> readIssues() {

    IssueReaderProvider reader;

    switch (SupportedListTypes.byConfig()) {
      case CSV:
      default:
        reader = new CSVReader();
        break;
    }
    String fileName = buildFileName();
    reader.readFile(fileName);

    return issues;
  }

  /**
   * Creates the full file name of the issue list based on the system properties.
   *
   * @return Full file name
   */
  String buildFileName() {
    return Config.ISSUELIST_DIRECTORY.asString() + File.separator + Config.ISSUELIST_FILENAME.asString() + "."
        + Config.ISSUELIST_EXTENSION.asString().toLowerCase(Locale.ROOT);
  }

}
