package com.hp.opta.flex.antlr.parser.impl;

import com.hp.opta.flex.antlr.model.ConfigMetaData;
import com.hp.opta.flex.antlr.parser.CustomParser;
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
        configFile ="token[3].name=MessageTime\n" +
                "token[3].type=TimeStamp";

        //"regex=fsdfsdfsdfsd" ;
    }


   // @Test
    public void testReadTokens() {
        ConfigMetaData configMetaData = parser.parse(configFile);

    }


}
