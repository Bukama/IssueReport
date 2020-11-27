
package com.github.bukama.ir.listreader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;

import com.github.bukama.ir.config.Config;
import com.github.bukama.ir.jaxb.IssueType;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

/**
 * Provides a reader for an issue list in a CSV format.
 */
public class IssueReportCSVReader implements IssueReaderProvider {

  private static final Logger LOG = Logger.getAnonymousLogger();

  private final Set<IssueType> issues = new HashSet<>();

  private int lineNumber = 0;

  @Override
  public Set<IssueType> readFile(String fileName) {

    ClassLoader classLoader = getClass().getClassLoader();

    CSVReader reader = null;
    try (
        InputStreamReader isr = new InputStreamReader(Objects.requireNonNull(classLoader.getResourceAsStream(fileName)),
            StandardCharsets.UTF_8)) {

      reader = new CSVReader(isr);
      String[] nextLine;

      while ((nextLine = reader.readNext()) != null) {
        processLine(nextLine);
      }
    } catch (FileNotFoundException e) {
      LOG.severe(String.format("Issue list not found at [%s]", fileName));
    } catch (IOException e) {
      LOG.severe(String.format("Error while accessing issue list: [%s]", e.getMessage()));
    } catch (CsvValidationException e) {
      LOG.severe(String.format("Issue list is not a valid CSV file: [%s]", e.getMessage()));
    }
    return issues;
  }

  /**
   * Splits the line into it parts and converts it into an issue which is then added to the set.
   * 
   * @param line
   *          line read from file
   */
  private void processLine(String[] line) {
    lineNumber++;
    // Maybe skip first line
    if (lineNumber == 1 && Config.ISSUELIST_CSV_SKIPFIRSTLINE.asBoolean()) {
      return;
    }

    if (line.length != 3) {
      throw new IllegalArgumentException(String.format("Line does not contain three elements, but [%s]", line.length));
    }

    issues.add(new IssueType(line[0], line[1], line[2]));
  }

}
