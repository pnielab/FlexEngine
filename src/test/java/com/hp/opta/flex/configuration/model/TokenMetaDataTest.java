package com.hp.opta.flex.configuration.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TokenMetaDataTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBasicConstructor() {
		TokenMetaData test = new TokenMetaData();
		Assert.assertEquals(-1, test.getIndex());
		Assert.assertNull(test.getName());
		Assert.assertNull(test.getFormat());
		Assert.assertNull(test.getType());
	}

	@Test
	public void testParameterConstructor() {
		TokenMetaData test = new TokenMetaData("testName", TokenType.Integer, "testFormat", 99);
		Assert.assertEquals(99, test.getIndex());
		Assert.assertEquals("testName",test.getName());
		Assert.assertEquals("testFormat",test.getFormat());
		Assert.assertEquals(TokenType.Integer,test.getType());
	}
	
	
}
