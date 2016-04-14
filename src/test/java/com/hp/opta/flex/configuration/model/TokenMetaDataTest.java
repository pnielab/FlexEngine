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
	
	
	@Test
	public void testHashCode() {
		TokenMetaData t1 = new TokenMetaData("testName", TokenType.Integer, "testFormat", 99);
		TokenMetaData t2 = new TokenMetaData("testName", TokenType.Integer, "testFormat", 99);
		TokenMetaData t3 = new TokenMetaData("testName", TokenType.Integer, "testFormat", 9);
		
		Assert.assertEquals(t1.hashCode(),t1.hashCode());
		Assert.assertEquals(t1.hashCode(),t2.hashCode());
		Assert.assertNotEquals(t1.hashCode(), null);
		Assert.assertNotEquals(t1.hashCode(), t3.hashCode());
		
	}		
	
	
	@Test
	public void testEquals() {
		TokenMetaData t1 = new TokenMetaData("testName", TokenType.Integer, "testFormat", 99);
		TokenMetaData t2 = new TokenMetaData("testName", TokenType.Integer, "testFormat", 99);
		TokenMetaData t3 = new TokenMetaData("testName", TokenType.Integer, "testFormat", 9);
		
		Assert.assertTrue(t1.equals(t1));
		Assert.assertTrue(t1.equals(t2));
		Assert.assertFalse(t1.equals(t3));
		Assert.assertFalse(t1.equals(null));
		Assert.assertFalse(t1.equals(new String("Jurgen")));
		Assert.assertFalse(t1.equals(new TokenMetaData()));
		
	}	
	
	
}
