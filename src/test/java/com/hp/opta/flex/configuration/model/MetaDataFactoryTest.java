package com.hp.opta.flex.configuration.model;

import com.hp.opta.flex.antlr.exception.FlexEngineParseException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MetaDataFactoryTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCreateTokenMetaData() {
        TokenMetaData test = MetaDataFactory.createTokenMetaData(
                "testname_11:00:00", "Integer", "testFormat", "1", "1", "1");
        Assert.assertNotNull("The Object must not be null", test);
        Assert.assertEquals(1, test.getIndex());
        Assert.assertEquals("testname_11:00:00", test.getName());
        Assert.assertEquals("testFormat", test.getFormat());
        Assert.assertEquals(TokenType.Integer, test.getType());

        FlexEngineParseException formatError = null;
        try {
            test = MetaDataFactory.createTokenMetaData(
                    "testname_11:00:00", "Integer", "testFormat", "1", "2", "3");
        } catch (FlexEngineParseException e) {
            formatError = e;
        }
        Assert.assertNotNull(formatError);
    }

}
