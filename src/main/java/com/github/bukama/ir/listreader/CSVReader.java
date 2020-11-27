
package com.github.bukama.ir.listreader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Stream;

import com.github.bukama.ir.config.Config;
import com.github.bukama.ir.jaxb.IssueType;

/**
 * Provides a reader for an issue list in a CSV format.
 */
public class CSVReader implements IssueReaderProvider {

  private static final Logger LOG = Logger.getAnonymousLogger();

  private Set<IssueType> issues = new HashSet<>();

  private int lineNumber = 0;

  public Set<IssueType> readFile(String fileName) {

    try (Stream<String> lines = Files.lines(Paths.get(fileName), StandardCharsets.UTF_8)) {
      lines.forEachOrdered(this::processLine);
    } catch (IOException ex) {
      LOG.severe(String.format("Issue list not found at [%s]", fileName));
    }

    return issues;
  }

  private void processLine(String line) {
    lineNumber++;
    // Maybe skip first line
    if (lineNumber == 1 && Config.ISSUELIST_CSV_SKIPFIRSTLINE.asBoolean()) {
      return;
    }

    // TODO keep on coding :O

  }
}
