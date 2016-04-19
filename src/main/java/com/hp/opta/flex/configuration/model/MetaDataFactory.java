/*
 * 
 */
package com.hp.opta.flex.configuration.model;

import com.hp.opta.flex.antlr.parser.Validator;
import com.hp.opta.flex.antlr.parser.impl.ValidatorImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A factory for creating MetaData objects.
 */
public class MetaDataFactory {

    private static final Logger logger = LoggerFactory.getLogger(MetaDataFactory.class);

    private static Validator validator = ValidatorImpl.getInstance();


    /**
     * Creates a new MetaData object.
     *
     * @param name   the name
     * @param type   the type
     * @param format the format
     * @param index1 the index1
     * @param index2 the index2
     * @param index3 the index3
     * @return the token meta data
     */
    public static TokenMetaData createTokenMetaData(String name, String type, String format, String index1, String index2, String index3, ConfigurationMetaData configurationMetaData) {
        validator.validateTokenIndex(name, index1, index2, index3, configurationMetaData);
        //default value of type is String
        TokenType tokenType = (type == null) ? TokenType.String : TokenType.valueOf(type);
        validator.validateFormat(tokenType, format);
        return new TokenMetaData(name, tokenType, format, Integer.parseInt(index1));
    }


    public static void addTokenMetaData(TokenMetaData tokenMetaData, ConfigurationMetaData configurationMetaData) {
        if (validator.canAddToken(tokenMetaData, configurationMetaData)) {
            configurationMetaData.addToken(tokenMetaData);
        }
    }
}
