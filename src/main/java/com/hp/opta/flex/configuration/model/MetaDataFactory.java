/*
 * 
 */
package com.hp.opta.flex.configuration.model;

import com.hp.opta.flex.antlr.exception.FlexEngineParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * A factory for creating MetaData objects.
 */
public class MetaDataFactory {


    private static final Logger logger = LoggerFactory.getLogger(MetaDataFactory.class);
    //TODO: get dateFormats from config file:
    private static final Map<String, String> dateFormats;

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
    public static TokenMetaData createTokenMetaData(String name, String type, String format,
                                                    String index1, String index2, String index3) {

        if (index2 != null && index3 != null && ((!index1.equals(index2)) || (!index2.equals(index3)) || (!index1.equals(index3)))) {
            logger.error("index do not match when trying to parse token with name: {}, index1: {}, index2: {}, index3: {}", name, index1, index2, index3);
            throw new FlexEngineParseException("index do not match when trying to parse token with name: " + name);
        }

        //default value of type is String
        TokenType tokenType = (type == null) ? TokenType.String : TokenType.valueOf(type);
        if (TokenType.TimeStamp.equals(tokenType) && dateFormats.get(format) == null) {
            logger.error("unsupported date format: {}, should be one of the given format: {}", format, dateFormats);
            throw new FlexEngineParseException("unsupported date format: " + format);
        }
        return new TokenMetaData(name, tokenType, format, Integer.parseInt(index1));
    }
}
