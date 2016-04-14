package com.hp.opta.flex.configuration.model;

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
                "testName", "Integer", "testFormat", "1", "1", "1");
        Assert.assertNotNull("The Object must not be null", test);
        Assert.assertEquals(1, test.getIndex());
        Assert.assertEquals("testName", test.getName());
        Assert.assertEquals("testFormat", test.getFormat());
        Assert.assertEquals(TokenType.Integer, test.getType());


        test = MetaDataFactory.createTokenMetaData(
                "testName", "Integer", "testFormat", "1", "2", "3");
        Assert.assertNull("The Object should be null", test);
    }

}
