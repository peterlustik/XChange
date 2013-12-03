package com.xeiam.xchange.cryptsy.dto.cryptsy.marketdata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CryptsySingleOrderbook {
	private final Logger log = LoggerFactory.getLogger(CryptsyDepthResult.class);
	
	private final CryptsyDepth depth;
	/**
	 * Constructor
	 * 
	 * @param error
	 *            array of string error messages
	 * @param result
	 *            the returned depths
	 */
	public CryptsySingleOrderbook(@JsonProperty("DGC") CryptsyDepth depth) {
		this.depth = depth;
		log.debug("Created CryptsyDepthResult");
	}
}
