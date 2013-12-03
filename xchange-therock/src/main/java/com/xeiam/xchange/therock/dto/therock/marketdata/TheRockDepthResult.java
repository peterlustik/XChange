package com.xeiam.xchange.therock.dto.therock.marketdata;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xeiam.xchange.therock.dto.therock.TheRockResult;

public class TheRockDepthResult extends TheRockResult<Map<String, TheRockDepth>> {

	/**
	 * Constructor
	 * 
	 * @param error
	 *            array of string error messages
	 * @param result
	 *            the returned depths
	 */
	public TheRockDepthResult(@JsonProperty("error") String error, @JsonProperty("result") Map<String, TheRockDepth> result) {
		super(result, error);
	}
}