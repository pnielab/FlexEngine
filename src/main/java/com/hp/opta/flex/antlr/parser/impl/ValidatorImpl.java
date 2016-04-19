package com.hp.opta.flex.antlr.parser.impl;

import com.hp.opta.flex.antlr.exception.FlexEngineParseException;
import com.hp.opta.flex.antlr.parser.Validator;
import com.hp.opta.flex.configuration.model.ConfigurationMetaData;
import com.hp.opta.flex.configuration.model.TokenMetaData;
import com.hp.opta.flex.configuration.model.TokenType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zeev on 4/19/16.
 */
public class ValidatorImpl implements Validator {

    private static final Logger logger = LoggerFactory.getLogger(ValidatorImpl.class);

    private static final int LEGAL_SEQUENCE = 1;
    //TODO: get dateFormats from config file:
    private static final Map<String, String> dateFormats;
    private static Validator instance = new ValidatorImpl();

    static {
        dateFormats = new HashMap<String, String>();
        dateFormats.put("epoch", "epoch");
        dateFormats.put("MM-dd-yyyy", "MM-dd-yyyy");
        dateFormats.put("yyyy-MM-dd", "yyyy-MM-dd");
        dateFormats.put("MM/dd/yyyy", "MM/dd/yyyy");
        dateFormats.put("yyyy/MM/dd", "yyyy/MM/dd");
        dateFormats.put("dd MMM yyyy", "dd MMM yyyy");
        dateFormats.put("dd MMMM yyyy", "dd MMMM yyyy");
    }

    private ValidatorImpl() {
    }

    public static Validator getInstance() {
        return instance;
    }

    @Override
    public void validateTokenIndex(String name, String index1, String index2, String index3, ConfigurationMetaData configurationMetaData) {
        int currentTokenIndex = configurationMetaData.getNumberOfTokens() - 1;
        //validate same index for the same token
        if (index2 != null && index3 != null && ((!index1.equals(index2)) || (!index2.equals(index3)) || (!index1.equals(index3)))) {
            logger.error("index do not match when trying to parse token with name: {}, index1: {}, index2: {}, index3: {}", name, index1, index2, index3);
            throw new FlexEngineParseException("index do not match when trying to parse token with name: " + name);
        }
        //validate new token sequence
        int givenTokenIndex = Integer.parseInt(index1);
        if ((givenTokenIndex - currentTokenIndex) > LEGAL_SEQUENCE) {
            logger.error("sequence is illegal: {}, should be: {}, token with name: {} failed parsing", (givenTokenIndex - currentTokenIndex), LEGAL_SEQUENCE, name);
            throw new FlexEngineParseException("sequence is illegal: " + (givenTokenIndex - currentTokenIndex) + ", should be: " + LEGAL_SEQUENCE + ", token with name:" + name + " failed parsing");
        }
        //validate wrong order or duplication
        if (givenTokenIndex <= currentTokenIndex) {
            logger.error("new token index is in wrong order or duplication, index should be: {}, but got: {}, token with name: {} failed parsing ", currentTokenIndex + 1, givenTokenIndex, name);
            throw new FlexEngineParseException("new token index is in wrong order or duplication, index should be: " + (currentTokenIndex + 1) + ", but got: " + givenTokenIndex + ", token with name:" + name + " failed parsing");
        }
    }

    @Override
    public void validateFormat(TokenType tokenType, String format) {
        if (TokenType.TimeStamp.equals(tokenType) && dateFormats.get(format) == null) {
            logger.error("unsupported date format: {}, should be one of the given format: {}", format, dateFormats);
            throw new FlexEngineParseException("unsupported date format: " + format);
        }
    }

    @Override
    public boolean canAddToken(TokenMetaData tokenMetaData, ConfigurationMetaData configurationMetaData) {
        return (configurationMetaData.getTokenCount() < configurationMetaData.getNumberOfTokens());
    }

    @Override
    public void validateTokenCount(ConfigurationMetaData configMetaData) {
        if (configMetaData.getTokenCount() > configMetaData.getNumberOfTokens()) {
            logger.error("number of tokens: {} is less than token count: {}", configMetaData.getNumberOfTokens(), configMetaData.getTokenCount());
            throw new FlexEngineParseException("number of tokens: " + configMetaData.getNumberOfTokens() + ", is less than token count: " + configMetaData.getTokenCount());
        }
    }
}
