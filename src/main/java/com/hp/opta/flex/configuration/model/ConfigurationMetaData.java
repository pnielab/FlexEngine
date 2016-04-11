package com.hp.opta.flex.configuration.model;

import java.util.List;
import java.util.Map;

import com.hp.opta.flex.configuration.model.tree.Node;


/**
 * The Class ConfigurationMetaData.
 */
public class ConfigurationMetaData {

    /** The parse string. */
    private String parseString;
    
    /** The tokens. */
    private List<TokenMetaData> tokens;
    
    /** The event parse root. */
    private Map<String,Node<? extends Object>> eventMappings;

    /** The Constant comparator. */
    private static final TokenMetaDataIndexComparator comparator = new TokenMetaDataIndexComparator();
    
    
    
	/**
	 * Gets the parses the string.
	 *
	 * @return the parses the string
	 */
	public String getParseString() {
		return parseString;
	}

	/**
	 * Sets the parses the string.
	 *
	 * @param parseString the new parses the string
	 */
	public void setParseString(String parseString) {
		this.parseString = parseString;
	}

	/**
	 * Gets the tokens.
	 *
	 * @return the tokens
	 */
	public List<TokenMetaData> getTokens() {
		return tokens;
	}

	/**
	 * Sets the tokens.
	 *
	 * @param tokens the new tokens
	 */
	public void setTokens(List<TokenMetaData> tokens) {
		this.tokens = tokens;;
		this.tokens.sort(comparator);
	}

	/**
	 * Gets the event mappings.
	 *
	 * @return the event mappings
	 */
	public Map<String, Node<? extends Object>> getEventMappings() {
		return eventMappings;
	}

	/**
	 * Sets the event mappings.
	 *
	 * @param eventMappings the event mappings
	 */
	public void setEventMappings(Map<String, Node<? extends Object>> eventMappings) {
		this.eventMappings = eventMappings;
	}
    
  
    

}
