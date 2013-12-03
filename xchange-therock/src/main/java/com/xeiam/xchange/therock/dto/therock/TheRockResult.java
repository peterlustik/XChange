package com.xeiam.xchange.therock.dto.therock;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Raphael Voellmy
 */
public class TheRockResult<V> {

  private final V result;
  private final String error;

  /**
   * Constructor
   * 
   * @param result
   * @param error
   */
  @JsonCreator
  public TheRockResult(@JsonProperty("return") V result, @JsonProperty("error") String error) {

    this.result = result;
    this.error = error;
  }

  public boolean isSuccess() {

    return error == null;
  }

  public V getResult() {

    return result;
  }

  public String[] getError() {

    return new String[]{error};
  }

  @Override
  public String toString() {

    return String.format("TheResult[%s: %s]", isSuccess() ? "OK" : "error", isSuccess() ? result.toString() : error);
  }
}
