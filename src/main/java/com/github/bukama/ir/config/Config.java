
package com.github.bukama.ir.config;

public enum Config {

  /**
   * Directory of the issue list.
   * Default: "."
   */
  ISSUELIST_DIRECTORY(
      "com.github.bukama.ir.issuelist.directory",
      "."),
  /**
   * Filename of the issue list without extension or path.
   * Default: "issuelist"
   */
  ISSUELIST_FILENAME(
      "com.github.bukama.ir.issuelist.filename",
      "issuelist"),
  /**
   * File extension of the issue list.
   * Default: "csv"
   */
  ISSUELIST_EXTENSION(
      "com.github.bukama.ir.issuelist.extension",
      "csv");

  private final String key;
  private final String defaultValue;

  Config(String key, String defaultValue) {
    this.key = key;
    this.defaultValue = defaultValue;
  }

  /**
   * Reads the configuration from the system properties.
   * 
   * @return Read value or default value if not set.
   */
  public String value() {
    return System.getProperty(this.key, this.defaultValue);
  }
}
