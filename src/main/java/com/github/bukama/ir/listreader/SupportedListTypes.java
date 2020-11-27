
package com.github.bukama.ir.listreader;

import java.util.Locale;

import com.github.bukama.ir.config.Config;

/**
 * Enumeration which defines the supported file extensions for an issue list.
 */
public enum SupportedListTypes {

  /**
   * Comma separated value.
   */
  CSV();

  SupportedListTypes() {
  }

  /**
   * Retrieves the instance by the value configured in {@link Config#ISSUELIST_EXTENSION}.
   * 
   * @return Retrieved instance for the format
   * @throws IllegalArgumentException
   *           when configured value is not a valid entry of the enumeration
   */
  public static SupportedListTypes byConfig() throws IllegalArgumentException {
    // This throws an {@link IllegalArgumentException} if value is not in enum, so no need to check this manually.
    return SupportedListTypes.valueOf(Config.ISSUELIST_EXTENSION.asString().trim().toUpperCase(Locale.ROOT));
  }
}
