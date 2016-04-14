package com.hp.opta.flex.configuration;

import java.util.regex.Pattern;

import com.hp.opta.flex.configuration.model.ConfigurationMetaData;

/**
 * The Class EventParsingData.
 */
public class EventParsingData {
	
	/** The configuration meta data. */
	private ConfigurationMetaData configurationMetaData;
	
	/** The parse pattern. */
	private Pattern parsePattern;

	/**
	 * Instantiates a new event parsing data.
	 *
	 * @param configurationMetaData the configuration meta data
	 */
	public EventParsingData(ConfigurationMetaData configurationMetaData) {
		this.configurationMetaData = configurationMetaData;
	}
	
	
	/**
	 * Initialize.
	 */
	public void initialize(){
		
		String str = configurationMetaData.getParseString();
		if((str == null) || (str.isEmpty())){
			throw new IllegalStateException("Could not initialize Parsing Pattern. Parsing String is empty");
		}
		
		if(configurationMetaData.getParsingMethod() == null){
			throw new IllegalStateException("Could not initialize Parsing Pattern. Parsing Method does not exist");
		}
		
		switch(configurationMetaData.getParsingMethod()){
		case REGEX:
			this.parsePattern = Pattern.compile(configurationMetaData.getParseString());
			break;
			default:
				throw new IllegalStateException("Could not initialize Parsing Pattern. Parsing Method invalid");
		}
		
		
	}
	

	/**
	 * Gets the configuration meta data.
	 *
	 * @return the configuration meta data
	 */
	public ConfigurationMetaData getConfigurationMetaData() {
		return configurationMetaData;
	}

	/**
	 * Sets the configuration meta data.
	 *
	 * @param configurationMetaData the new configuration meta data
	 */
	public void setConfigurationMetaData(ConfigurationMetaData configurationMetaData) {
		this.configurationMetaData = configurationMetaData;
	}

	/**
	 * Gets the parses the pattern.
	 *
	 * @return the parses the pattern
	 */
	public Pattern getParsePattern() {
		return parsePattern;
	}

	/**
	 * Sets the parses the pattern.
	 *
	 * @param parsePattern the new parses the pattern
	 */
	public void setParsePattern(Pattern parsePattern) {
		this.parsePattern = parsePattern;
	}
	

}
