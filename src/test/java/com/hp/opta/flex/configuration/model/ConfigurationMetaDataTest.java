package com.hp.opta.flex.configuration.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ConfigurationMetaDataTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBasic() {
		ConfigurationMetaData c = new ConfigurationMetaData(0);
		
		c.setParseString("New String");
		assertEquals("New String", c.getParseString());
	}

	
	@Test
	public void testAddRemove() {
		ConfigurationMetaData c = new ConfigurationMetaData(5);
		
		for(int i = 5; i > 0; i--){
			TokenMetaData t = new TokenMetaData("",TokenType.Integer,"",i);
			c.addToken(t);
		}
		
		assertNotNull(c.getTokens());
		Assert.assertTrue(c.getNumberOfTokens() == 5);
		
		StringBuilder resultAfterSet = new StringBuilder();
		
		for(TokenMetaData t : c.getTokens()){
			resultAfterSet.append(t.getIndex());
		}
		
	}
	
}
