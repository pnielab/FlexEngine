package com.hp.opta.flex.antlr.parser.impl;

import com.hp.opta.flex.antlr.exception.FlexEngineParseException;
import com.hp.opta.flex.antlr.parser.CustomParser;
import com.hp.opta.flex.configuration.model.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collection;
import java.util.Map;

/**
 * Created by zeev on 4/11/16.
 */
public class ParserImplTest {

    //The name can be in the following format: message_time (all low case, _ between words, no spaces), name_11:00:00
    //NAME_FORMAT     : ([a-z]*)'_'([0-2][0-4]':'[0-5][0-9]':'[0-5][0-9]);
    private static final String NAME = "name_11:00:00";
    private static final String REGEX = ".*(scan|inbound/pass[12]|outbound/smtp)\\[(\\d+)\\]:\\s*(?:([\\w.-]+)" +
            "\\[)?(\\d+\\.\\d+\\.\\d+\\.\\d+)\\]?\\s+(\\S+)\\s+(\\d+)\\s+(\\d+)\\s+(RECV|SCAN|SEND)\\s+(.*)";

    private static CustomParser parser;

    @BeforeClass
    public static void init() {
        parser = new ParserImpl();
    }

    @Test(expected = FlexEngineParseException.class)
    public void testParsingMissingRegexCorrectly() {
        String input = "token.count=1\n" +
                "token[0].name=" + NAME + '\n';
        ConfigurationMetaData configMetaData = parser.parse(input);
    }

    @Test
    public void testParsingRegexCorrectly() {
        String input = "regex=" + REGEX + '\n' +
                "token.count=1\n" +
                "token[0].name=" + NAME + '\n';
        ConfigurationMetaData configMetaData = parser.parse(input);
        Assert.assertTrue(configMetaData.getParseString().equals(REGEX));
    }


    @Test(expected = FlexEngineParseException.class)
    public void testCheckFailureWhenNameIsMissing() {
        String input = "regex=" + REGEX + '\n' +
                "token.count=1\n";
        ConfigurationMetaData configMetaData = parser.parse(input);
    }

    @Test
    public void testCheckNameIsParsedCorrectlyAndNotMissing() {
        String input = "regex=" + REGEX + '\n' +
                "token.count=1\n" +
                "token[0].name=" + NAME + '\n';
        ConfigurationMetaData configMetaData = parser.parse(input);
        Assert.assertNotNull(configMetaData);
        Assert.assertNotNull(configMetaData.getTokens());
        Assert.assertEquals(configMetaData.getTokens().size(), 1);
        TokenMetaData token = configMetaData.getTokens().get(0);
        Assert.assertTrue(NAME.equals(token.getName()));
    }

    @Test(expected = FlexEngineParseException.class)
    public void testCheckNameIsWithIllegalCaracters() {
        String input = "regex=" + REGEX + '\n' +
                "token.count=1\n" +
                "token[0].name=" + "name_31:11:00\n";
        ConfigurationMetaData configMetaData = parser.parse(input);
    }

    @Test(expected = FlexEngineParseException.class)
    public void testCheckNameIsMissing() {
        String input = "regex=" + REGEX + '\n' +
                "token.count=1\n";
        ConfigurationMetaData configMetaData = parser.parse(input);
    }


    @Test
    public void testCheckTypeIsTimeStampAndCorrectFormatIsGiven() {

        String format = getSupportedFormat();
        String configFile = "regex=" + REGEX + '\n' +
                "token.count=1\n" +
                "token[0].name=" + NAME + "\n" +
                "token[0].type=" + TokenType.TimeStamp.name() + "\n" +
                "token[0].format=" + format + '\n';
        ConfigurationMetaData configMetaData = parser.parse(configFile);
    }

    @Test(expected = FlexEngineParseException.class)
    public void testCheckTypeIsTimeStampAndIncorrectFormatIsGiven() {

        String configFile = "regex=" + REGEX + '\n' +
                "token.count=1\n" +
                "token[0].name=" + NAME + "\n" +
                "token[0].type=" + TokenType.TimeStamp.name() + "\n" +
                "token[0].format=" + "wrongformat\n";
        ConfigurationMetaData configMetaData = parser.parse(configFile);
    }

    @Test(expected = FlexEngineParseException.class)
    public void testCheckTypeIsTimeStampAndNoFormatIsGiven() {
        String configFile = "regex=" + REGEX + '\n' +
                "token.count=1\n" +
                "token[0].name=" + NAME + "\n" +
                "token[0].type=" + TokenType.TimeStamp.name() + '\n';
        ConfigurationMetaData configMetaData = parser.parse(configFile);
    }

    @Test
    public void testCheckTypeIsNoTimeStamp() {
        String configFile = "regex=" + REGEX + '\n' +
                "token.count=1\n" +
                "token[0].name=" + NAME + "\n" +
                "token[0].type=" + TokenType.Integer.name() + '\n';
        ConfigurationMetaData configMetaData = parser.parse(configFile);
        TokenMetaData token = configMetaData.getTokens().get(0);
        Assert.assertEquals(TokenType.Integer, token.getType());
        Assert.assertNull(token.getFormat());
    }


    @Test
    public void testReadTokens() {
        String format = getSupportedFormat();
        String configFile = "regex=" + REGEX + '\n' +
                "token.count=2\n" +
                "token[0].name=" + NAME + '\n' +
                "token[1].name=" + NAME + '\n'
                + "token[1].type=" + TokenType.TimeStamp.name() + '\n'
                + "token[1].format=" + format + '\n';
        ;

        ConfigurationMetaData configMetaData = parser.parse(configFile);
        Assert.assertNotNull(configMetaData);
        Assert.assertNotNull(configMetaData.getParseString());
        Assert.assertEquals(configMetaData.getParsingMethod(), ParsingMethod.REGEX);
        Assert.assertNotNull(configMetaData.getTokens());
        Assert.assertEquals(configMetaData.getTokens().size(), 2);
        Assert.assertEquals(configMetaData.getTokenCount(), 2);
        Assert.assertTrue(configMetaData.getParseString().equals(REGEX));

        Assert.assertTrue(NAME.equals(configMetaData.getTokens().get(0).getName()));
        Assert.assertNull(configMetaData.getTokens().get(0).getType());
        Assert.assertNull(configMetaData.getTokens().get(0).getFormat());

        Assert.assertTrue(NAME.equals(configMetaData.getTokens().get(1).getName()));
        Assert.assertTrue(configMetaData.getTokens().get(1).getType() == TokenType.TimeStamp);
        Assert.assertTrue(configMetaData.getTokens().get(1).getFormat().equals(format));
    }

    private String getSupportedFormat() {
        Map<String, String> dateFormats = (Map<String, String>) ReflectionTestUtils.getField(new MetaDataFactory(), "dateFormats");
        Assert.assertNotNull(dateFormats);
        Collection<String> values = dateFormats.values();
        Assert.assertTrue(values.size() > 0);
        return values.stream().findFirst().get();
    }


}
