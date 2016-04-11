package com.hp.opta.flex.configuration.model;

/**
 * The Class TokenMetaData.
 */
public class TokenMetaData {

	/** The index. */
	private int index;
    
    /** The name. */
    private String name;
    
    /** The type. */
    private TokenType type;
    
    /** The format. */
    private String format;
    
    /**
     * Instantiates a new token meta data.
     */
    public TokenMetaData() {
    	this.name = null;
    	this.type = null;
    	this.format = null;
    	this.index = -1;
	}

    
	/**
	 * Instantiates a new token meta data.
	 *
	 * @param index the index
	 * @param name the name
	 * @param type the type
	 * @param format the format
	 */
	public TokenMetaData(String name, TokenType type, String format, int index) {
		this.name = name;
		this.type = type;
		this.format = format;
		this.index = index;
	}

	/**
	 * Gets the index.
	 *
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}
	
	/**
	 * Sets the index.
	 *
	 * @param index the new index
	 */
	public void setIndex(int index) {
		this.index = index;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public TokenType getType() {
		return type;
	}
	
	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(TokenType type) {
		this.type = type;
	}
	
	/**
	 * Gets the format.
	 *
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}
	
	/**
	 * Sets the format.
	 *
	 * @param format the new format
	 */
	public void setFormat(String format) {
		this.format = format;
	}
    
}
