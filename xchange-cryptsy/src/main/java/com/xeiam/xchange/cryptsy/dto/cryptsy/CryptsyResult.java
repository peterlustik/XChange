package com.xeiam.xchange.cryptsy.dto.cryptsy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Raphael Voellmy
 */
public class CryptsyResult<V> {

	private final Logger log = LoggerFactory.getLogger(CryptsyResult.class);

	private final V result;
	private final String error;
	private final int success;

	/**
	 * Constructor
	 * 
	 * @param success
	 *            either a 1 or a 0. 1 Represents sucessful call, 0 Represents
	 *            unsuccessful
	 * @param error
	 *            if unsuccessful, this will be the error message
	 * @param result
	 *            the returned depths
	 */
	@JsonCreator
	public CryptsyResult(@JsonProperty("return") V result, @JsonProperty("error") String error, @JsonProperty("success") int success) {
		log.debug("Created CryptsyResult");
		this.result = result;
		this.error = error;
		this.success = success;
	}

	public boolean isSuccess() {

		return success == 1;
	}

	public V getResult() {

		return result;
	}

	public String getError() {

		return error;
	}

	@Override
	public String toString() {

		return String.format("TheResult[%s: %s]", isSuccess() ? "OK" : "error", isSuccess() ? result.toString() : error);
	}
}
