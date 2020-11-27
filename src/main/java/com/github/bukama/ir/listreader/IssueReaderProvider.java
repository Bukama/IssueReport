
package com.github.bukama.ir.listreader;

import java.util.Set;

import com.github.bukama.ir.jaxb.IssueType;

/**
 * An implementing class provides a reader for import a list of issues.
 */
public interface IssueReaderProvider {

  /**
   * Read the issue list file
   * 
   * @param fileName
   *          Name of the issue list
   * @return Set with all issues read from the file
   */
  Set<IssueType> readFile(String fileName);
}
