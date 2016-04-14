package com.hp.opta.flex.execution;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.hp.opta.flex.configuration.EventParsingData;
import com.hp.opta.flex.execution.parsers.DefaultEventParser;

/**
 * The Class FilterExcecutionHolder.
 */
public class ParsingExcecutioner {
	
	
	/** The event filter map. */
	private static Map<String, EventParser<? extends Object, ? extends Object>> eventParserMap = 
			new ConcurrentHashMap<>();
	
	
	
	/**
	 * Publish.
	 *
	 * @param sourceType the source type
	 * @param data the data
	 */
	public static void publish(String sourceType,EventParsingData data){
		EventParser<Object, Object> filter = new DefaultEventParser<>(data);
		eventParserMap.put(sourceType, filter);
	}
	
	
	
	/**
	 * Run through filter.
	 *
	 * @param sourceType the source type
	 * @param event the event
	 * @return the object
	 */
	public static Object runThroughFilter(String sourceType, Object event){
		EventParser parser = getEventParser(sourceType);
		return parser.parse(event);
	}
	
	
	/**
	 * Gets the event filter.
	 *
	 * @param sourceType the source type
	 * @return the event filter
	 */
	private static EventParser<? extends Object, ? extends Object> getEventParser(String sourceType){
		return eventParserMap.get(sourceType);
	}	

}
