package com.hp.opta.flex.configuration.model;

import com.hp.opta.flex.configuration.model.tree.Node;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * The Class ConfigurationMetaData.
 */
public class ConfigurationMetaData implements Serializable{


    /**
     * The Constant comparator.
     */
    private static final TokenMetaDataIndexComparator comparator = new TokenMetaDataIndexComparator();
    
    
    /** The parsing method. */
    private ParsingMethod parsingMethod;

    /** The token count. */
    private int tokenCount;

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
        tokens = new LinkedList<>();
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
        return tokenCount;
    }

    /**
     * Sets the token count.
     *
     * @param tokenCount the new token count
     */
    public void setTokenCount(int tokenCount) {
        this.tokenCount = tokenCount;
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
        this.tokens = tokens;
        ;
        this.tokens.sort(comparator);
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
     * Removes the token.
     *
     * @param token the token
     */
    public void removeToken(TokenMetaData token) {
        this.tokens.remove(token);
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
