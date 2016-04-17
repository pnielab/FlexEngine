package com.hp.opta.flex.configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.hp.opta.flex.configuration.model.ConfigurationMetaData;

/**
 * The Class ConfigurationMetadaDataHolder.
 */
public class EventParsingDataHolder {
	
	/** The configurations. */
	private static Map<String, EventParsingData> configurations = new ConcurrentHashMap<>();
	
	
	/**
	 * Adds the configuration metada data.
	 *
	 * @param identifier the identifier
	 * @param metaData the meta data
	 */
	public static EventParsingData publish(String sourceType, ConfigurationMetaData metaData){
		EventParsingData eventParsingData = new EventParsingData(metaData);
		eventParsingData.initialize();
		return configurations.put(sourceType, eventParsingData);
	}
	
	
	/**
	 * Gets the configuration metada data.
	 *
	 * @param identifier the identifier
	 * @return the configuration metada data
	 */
	public static EventParsingData get(String sourceType){
		return configurations.get(sourceType);
	}
	
	
	/**
	 * Removes the configuration metada data.
	 *
	 * @param identifier the identifier
	 * @return the configuration meta data
	 */
	public static EventParsingData remove(String sourceType){
		return configurations.remove(sourceType);
	}
	
	
	
	
	
	
	

}
