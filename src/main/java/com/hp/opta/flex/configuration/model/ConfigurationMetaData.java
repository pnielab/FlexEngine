package com.hp.opta.flex.configuration.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.hp.opta.flex.configuration.model.tree.Node;


/**
 * The Class ConfigurationMetaData.
 *
 * @author yoavn
 * @since Apr 17, 2016
 */
public class ConfigurationMetaData implements Serializable{



	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

    /** The Constant comparator. */
    private static final TokenMetaDataIndexComparator comparator = new TokenMetaDataIndexComparator();

    /** The parsing method. */
    private ParsingMethod parsingMethod;

    /** The parse string.*/
    private String parseString;

    /**The tokens.*/
    private List<TokenMetaData> tokens;

    /**The event parse root.*/
    private Map<String, Node<? extends Object>> eventMappings;


    /**
     * Instantiates a new configuration meta data.
     */
    public ConfigurationMetaData() {
        tokens = new ArrayList<>();
        eventMappings = new HashMap<>();
    }


    /**
     * Instantiates a new configuration meta data.
     *
     * @param tokenCount the token count
     */
    public ConfigurationMetaData(int tokenCount) {
        tokens = new ArrayList<>(tokenCount);
        eventMappings = new HashMap<>();
    }



    /**
     * Gets the parsing method.
     *
     * @return the parsing method
     */
    public ParsingMethod getParsingMethod() {
        return parsingMethod;
    }

    /**
     * Sets the parsing method.
     *
     * @param parsingMethod the new parsing method
     */
    public void setParsingMethod(ParsingMethod parsingMethod) {
        this.parsingMethod = parsingMethod;
    }

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
     * Gets the token count.
     *
     * @return the token count
     */
    public int getTokenCount() {
        return tokens.size();
    }

    /**
     * Sets the token count.
     *
     * @param tokenCount the new token count
     */
    public void setTokenCount(int tokenCount) {
    	tokens = new ArrayList<>(tokenCount);
    }


    /**
     * Gets the tokens iterator.
     *
     * @return the tokens iterator
     */
    public Iterable<TokenMetaData> getTokens() {
        return tokens;
    }


    /**
     * Adds a token.
     *
     * @param token the token
     */
    public void addToken(TokenMetaData token) {
        this.tokens.add(token);
        this.tokens.sort(comparator);
    }


    /**
     * Gets the event mappings.
     *
     * @return the event mappings
     */
    public Iterable<Entry<String, Node<? extends Object>>> getEventMappings() {
    	return eventMappings.entrySet();
    }


    /**
     * Adds the event mappings.
     *
     * @param key  the key
     * @param node the node
     */
    public void addEventMapping(String key, Node<? extends Object> node) {
        this.eventMappings.put(key, node);
    }


    /**
     * Removes the event mapping.
     *
     * @param key the key
     */
    public void removeEventMapping(String key) {
        this.eventMappings.remove(key);
    }


}
