package com.hp.opta.flex.execution.parsers;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

import com.hp.opta.flex.configuration.EventParsingData;
import com.hp.opta.flex.configuration.model.TokenMetaData;
import com.hp.opta.flex.execution.EventParser;

/**
 * The Class EventFilterMapImpl.
 *
 * @param <T> the generic type
 * @param <R> the generic type
 */
public class DefaultEventParser<T extends Object, R extends Object> implements EventParser<T, R>{

	/** The parsing data. */
	private EventParsingData parsingData;
	
	/**
	 * Instantiates a new event filter map impl.
	 */
	public DefaultEventParser() {
	}
	
	/**
	 * Instantiates a new event filter map impl.
	 *
	 * @param parsingData the parsing data
	 */
	public DefaultEventParser(EventParsingData parsingData) {
		this.parsingData = parsingData;
	}


	/* (non-Javadoc)
	 * @see com.hp.opta.flex.execution.EventFilter#apply(java.lang.Object)
	 */
	@Override
	public R parse(T input) {
		
		Matcher matcher = parsingData.getParsePattern().matcher(String.valueOf(input));
		matcher.find();
		
		Iterable<TokenMetaData> tokens = parsingData.getConfigurationMetaData().getTokens();
		
		Map<String, String> map = new HashMap<>(parsingData.getConfigurationMetaData().getTokenCount(), 1f);
		
		tokens.forEach((tmd) -> map.put(tmd.getName(), matcher.group(tmd.getIndex())));
		
		return (R)map;
	}

	/**
	 * Gets the parsing data.
	 *
	 * @return the parsing data
	 */
	public EventParsingData getParsingData() {
		return parsingData;
	}

	
	/**
	 * Sets the parsing data.
	 *
	 * @param parsingData the new parsing data
	 */
	public void setParsingData(EventParsingData parsingData) {
		this.parsingData = parsingData;
	}
	
	
	
	
}
