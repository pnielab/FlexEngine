package com.hp.opta.flex.antlr.parser;

import com.hp.opta.flex.configuration.model.ConfigurationMetaData;
import com.hp.opta.flex.configuration.model.TokenMetaData;
import com.hp.opta.flex.configuration.model.TokenType;

/**
 * Created by zeev on 4/19/16.
 */
public interface Validator {

    public void validateTokenIndex(String name, String index1, String index2, String index3, ConfigurationMetaData configurationMetaData);


    void validateFormat(TokenType tokenType, String format);

    boolean canAddToken(TokenMetaData tokenMetaData, ConfigurationMetaData configurationMetaData);

    void validateTokenCount(ConfigurationMetaData configMetaData);
}
