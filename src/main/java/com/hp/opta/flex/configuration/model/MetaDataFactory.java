/*
 * 
 */
package com.hp.opta.flex.configuration.model;

/**
 * A factory for creating MetaData objects.
 */
public class MetaDataFactory {
	
	
    /**
     * Creates a new MetaData object.
     *
     * @param name the name
     * @param type the type
     * @param format the format
     * @param index1 the index1
     * @param index2 the index2
     * @param index3 the index3
     * @return the token meta data
     */
    public static TokenMetaData createTokenMetaData(String name, String type, String format, 
    									int index1, int index2, int index3) {
        if ((index1 != index2) && (index2 != index3) && (index1 != index3) && (index3 != -1)) {
            return null;
        } else {
            return new TokenMetaData(name, TokenType.valueOf(type), format, index1);
        }
    }  

}
	