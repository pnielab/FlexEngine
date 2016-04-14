package com.hp.opta.flex.antlr.parser.impl;

import com.hp.opta.flex.antlr.exception.FlexEngineParseException;
import com.hp.opta.flex.antlr.parser.CustomParser;
import com.hp.opta.flex.configuration.model.ConfigurationMetaData;
import com.hp.opta.flex.configuration.model.ParsingMethod;
import com.hp.opta.flex.configuration.model.TokenMetaData;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by zeev on 4/11/16.
 */
public class ParserImplTest {

    //The name can be in the following format: message_time (all low case, _ between words, no spaces), name_11:00:00
    //NAME_FORMAT     : ([a-z]*)'_'([0-2][0-4]':'[0-5][0-9]':'[0-5][0-9]);
    private static final String NAME = "name_11:00:00";

    private static CustomParser parser;

    @BeforeClass
    public static void init() {
        parser = new ParserImpl();
    }

    @Test(expected = FlexEngineParseException.class)
    public void testCheckFailureWhenNameIsMissing() {
        String input = "regex=aregexexpretion\n" +
                "token.count=1";
        ConfigurationMetaData configMetaData = parser.parse(input);
    }

    @Test
    public void testCheckNameIsParsedCorrectlyAndNotMissing() {
        String input = "regex=aregexexpretion\n" +
                "token.count=1\n" +
                "token[0].name=" + NAME;

        ConfigurationMetaData configMetaData = parser.parse(input);
        Assert.assertNotNull(configMetaData);
        Assert.assertNotNull(configMetaData.getTokens());
        Assert.assertEquals(configMetaData.getTokens().size(), 1);
        TokenMetaData token = configMetaData.getTokens().get(0);
        Assert.assertTrue(NAME.equals(token.getName()));
    }

    @Test(expected = FlexEngineParseException.class)
    public void testCheckNameIsWithIllegalCaracters() {
        String input = "regex=aregexexpretion\n" +
                "token.count=1\n" +
                "token[0].name=" + "name_31:11:00";
        ConfigurationMetaData configMetaData = parser.parse(input);
    }

    @Test(expected = FlexEngineParseException.class)
    public void testCheckNameIsMissing() {
        String input = "regex=aregexexpretion\n" +
                "token.count=1";
        ConfigurationMetaData configMetaData = parser.parse(input);
    }


    @Test
    public void testReadTokens() {
        String configFile = "regex=scaninboundpass()outboundsmtp\n" +
                "token.count=1\n" +
                "token[0].name=" + NAME;

        ConfigurationMetaData configMetaData = parser.parse(configFile);
        Assert.assertNotNull(configMetaData);
        Assert.assertNotNull(configMetaData.getParseString());
        Assert.assertEquals(configMetaData.getParsingMethod(), ParsingMethod.REGEX);
        Assert.assertNotNull(configMetaData.getTokens());
        Assert.assertEquals(configMetaData.getTokens().size(), 1);
        Assert.assertEquals(configMetaData.getTokenCount(), 1);
        TokenMetaData token = configMetaData.getTokens().get(0);
        Assert.assertTrue(NAME.equals(token.getName()));
    }


}
