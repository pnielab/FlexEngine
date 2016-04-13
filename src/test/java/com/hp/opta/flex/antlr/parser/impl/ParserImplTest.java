package com.hp.opta.flex.antlr.parser.impl;

import com.hp.opta.flex.antlr.model.ConfigMetaData;
import com.hp.opta.flex.antlr.model.ParsingMethod;
import com.hp.opta.flex.antlr.parser.CustomParser;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by zeev on 4/11/16.
 */
public class ParserImplTest {


    private static String configFile;
    private static CustomParser parser;

    @BeforeClass
    public static void init() {
        parser = new ParserImpl();
        configFile = "regex=kjjzfkjhsdkjf\n" +
                "token.count=1\n" +
                "token[0].name=MessageTime\n" +
                "token[0].type=String";

    }


    @Test
    public void testReadTokens() {
        ConfigMetaData configMetaData = parser.parse(configFile);
        Assert.assertNotNull(configMetaData);
        Assert.assertNotNull(configMetaData.getParseString());
        Assert.assertEquals(configMetaData.getParsingMethod(), ParsingMethod.REGEX);
        Assert.assertNotNull(configMetaData.getTokens());
        Assert.assertEquals(configMetaData.getTokens().size(), 1);
        Assert.assertEquals(configMetaData.getTokenCount(), 1);
    }


}
