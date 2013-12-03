package com.xeiam.xchange.cryptsy.dto.cryptsy.marketdata;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xeiam.xchange.cryptsy.dto.cryptsy.CryptsyResult;

public class CryptsyDepthResult extends CryptsyResult<Map<String, CryptsyDepth>> {
	private final Logger log = LoggerFactory.getLogger(CryptsyDepthResult.class);
	
	/**
	 * Constructor
	 * 
	 * @param error
	 *            array of string error messages
	 * @param result
	 *            the returned depths
	 */
	public CryptsyDepthResult(@JsonProperty("success") int success, @JsonProperty("error") String error, @JsonProperty("result") Map<String, CryptsyDepth> result) {
		super(result, error, success);
		log.debug("Created CryptsyDepthResult");
	}
}