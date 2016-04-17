package com.hp.opta.flex.antlr.parser.impl;

import com.hp.opta.flex.antlr.exception.FlexEngineParseException;
import com.hp.opta.flex.antlr.parser.CustomParser;
import com.hp.opta.flex.configuration.model.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zeev on 4/11/16.
 */
public class ParserImplTest {

    //The name can be in the following format: message_time (all low case, _ between words, no spaces), name_11:00:00
    //NAME_FORMAT     : ([a-z]*)'_'([0-2][0-4]':'[0-5][0-9]':'[0-5][0-9]);
    private static final String NAME = "Message_time";
    private static final String REGEX = ".*(scan|inbound/pass[12]|outbound/smtp)\\[(\\d+)\\]:\\s*(?:([\\w.-]+)" +
            "\\[)?(\\d+\\.\\d+\\.\\d+\\.\\d+)\\]?\\s+(\\S+)\\s+(\\d+)\\s+(\\d+)\\s+(RECV|SCAN|SEND)\\s+(.*)";

    private static CustomParser parser;

    @BeforeClass
    public static void init() {
        parser = new ParserImpl();
    }

    @Test(expected = FlexEngineParseException.class)
    public void testCheckFailureWhenRegexIsMissing() {
        String input = "token.count=1\n" +
                "token[0].name=" + NAME + '\n';
        ConfigurationMetaData configMetaData = parser.parse(input);
    }

    @Test
    public void testParsingRegexCorrectly() {
        String input = "token.count=1\n" + "regex=" + REGEX + '\n' +
                "token[0].name=" + NAME + '\n';
        ConfigurationMetaData configMetaData = parser.parse(input);
        Assert.assertTrue(configMetaData.getParseString().equals(REGEX));
    }


    @Test(expected = FlexEngineParseException.class)
    public void testCheckFailureWhenNameIsMissing() {
        String input = "token.count=1\n" + "regex=" + REGEX + '\n';
        ConfigurationMetaData configMetaData = parser.parse(input);
    }

    @Test
    public void testCheckNameIsParsedCorrectlyAndNotMissing() {
        String input = "token.count=1\n" + "regex=" + REGEX + '\n' +
                "token[0].name=" + NAME + '\n';
        ConfigurationMetaData configMetaData = parser.parse(input);
        Assert.assertNotNull(configMetaData);
        Assert.assertNotNull(configMetaData.getTokens());
        Assert.assertEquals(configMetaData.getTokenCount(), 1);
        TokenMetaData token = configMetaData.getTokens().iterator().next();
        Assert.assertTrue(NAME.equals(token.getName()));
    }

    @Test(expected = FlexEngineParseException.class)
    public void testCheckNameIsWithIllegalCaracters() {
        String input = "regex=" + REGEX + '\n' +
                "token.count=1\n" +
                "token[0].name=" + "name_31:11:00\n";
        ConfigurationMetaData configMetaData = parser.parse(input);
    }

    @Test
    public void testCheckTypeIsTimeStampAndCorrectFormatIsGiven() {

        String format = getSupportedFormat();
        String configFile = "token.count=1\n" + "regex=" + REGEX + '\n' +
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
        String configFile = "token.count=1\n" + "regex=" + REGEX + '\n' +
                "token[0].name=" + NAME + "\n" +
                "token[0].type=" + TokenType.Integer.name() + '\n';
        ConfigurationMetaData configMetaData = parser.parse(configFile);
        TokenMetaData token = configMetaData.getTokens().iterator().next();
        Assert.assertEquals(TokenType.Integer, token.getType());
        Assert.assertNull(token.getFormat());
    }

    @Test(expected = FlexEngineParseException.class)
    public void testCheckTypeIsIllegal() {
        String configFile = "regex=" + REGEX + '\n' +
                "token.count=1\n" +
                "token[0].name=" + NAME + "\n" +
                "token[0].type=" + "IllegalFormat" + '\n';
        ConfigurationMetaData configMetaData = parser.parse(configFile);
    }


    @Test
    public void testReadTokens() {

        int tokenCount = 9;
        Map<String, String> names = new HashMap<String, String>();
        names.put("Process", "Process");
        names.put("ProcessID", "ProcessID");
        names.put("ClientHost", "ClientHost");
        names.put("ClientIP", "ClientIP");
        names.put("MessageID", "MessageID");
        names.put("Start", "Start");
        names.put("End", "End");
        names.put("Service", "Service");
        names.put("Info", "Info");


        String format = getSupportedFormat();


        String configFile = "token.count=" + tokenCount + '\n' + "regex=" + REGEX + '\n'
                + "token[0].name=" + names.get("Process") + '\n'
                + "token[1].name=" + names.get("ProcessID") + '\n'
                + "token[2].name=" + names.get("ClientHost") + '\n'
                + "token[3].name=" + names.get("ClientIP") + '\n'
                + "token[4].name=" + names.get("MessageID") + '\n'
                + "token[5].name=" + names.get("Start") + '\n'
                + "token[6].name=" + names.get("End") + '\n'
                + "token[7].name=" + names.get("Service") + '\n'
                + "token[8].name=" + names.get("Info") + '\n';


        ConfigurationMetaData configMetaData = parser.parse(configFile);
        Assert.assertNotNull(configMetaData);
        Assert.assertNotNull(configMetaData.getParseString());
        Assert.assertEquals(configMetaData.getParsingMethod(), ParsingMethod.REGEX);
        Assert.assertNotNull(configMetaData.getTokens());
        Assert.assertEquals(configMetaData.getTokenCount(), tokenCount);
        Assert.assertEquals(configMetaData.getTokenCount(), tokenCount);
        Assert.assertTrue(configMetaData.getParseString().equals(REGEX));

        for (TokenMetaData token : configMetaData.getTokens()) {
            Assert.assertNotNull(names.get(token.getName()));
            Assert.assertNull(token.getType());
            Assert.assertNull(token.getFormat());
        }
    }


    private String getSupportedFormat() {
        Map<String, String> dateFormats = (Map<String, String>) ReflectionTestUtils.getField(new MetaDataFactory(), "dateFormats");
        Assert.assertNotNull(dateFormats);
        Collection<String> values = dateFormats.values();
        Assert.assertTrue(values.size() > 0);
        return values.stream().findFirst().get();
    }


}
