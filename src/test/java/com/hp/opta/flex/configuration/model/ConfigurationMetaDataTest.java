package com.hp.opta.flex.configuration.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.LinkedList;
import java.util.List;

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
		ConfigurationMetaData c = new ConfigurationMetaData();
		
		c.setParseString("New String");
		assertEquals("New String", c.getParseString());
	}

	
	@Test
	public void testSetTokens() {
		ConfigurationMetaData c = new ConfigurationMetaData();

		List<TokenMetaData> list = new LinkedList<>();
		for(int i = 5; i > 0; i--){
			TokenMetaData t = new TokenMetaData();
			t.setIndex(i);
			list.add(t);
		}
		
		c.setTokens(list);
		assertNotNull(c.getTokens());
		Assert.assertTrue(c.getTokens().size() == 5);
		
		StringBuilder resultAfterSet = new StringBuilder();
		
		for(TokenMetaData t : c.getTokens()){
			resultAfterSet.append(t.getIndex());
		}

		Assert.assertEquals("12345", resultAfterSet.toString());
		
	}
	
	@Test
	public void testAddRemove() {
		ConfigurationMetaData c = new ConfigurationMetaData();
		
		for(int i = 5; i > 0; i--){
			TokenMetaData t = new TokenMetaData();
			t.setIndex(i);
			c.addToken(t);
		}
		
		assertNotNull(c.getTokens());
		Assert.assertTrue(c.getTokens().size() == 5);
		
		StringBuilder resultAfterSet = new StringBuilder();
		
		for(TokenMetaData t : c.getTokens()){
			resultAfterSet.append(t.getIndex());
		}
		
		TokenMetaData token = new TokenMetaData();
		token.setIndex(5);
		
		c.removeToken(token);
		
		resultAfterSet = new StringBuilder();
		
		for(TokenMetaData t : c.getTokens()){
			resultAfterSet.append(t.getIndex());
		}

		Assert.assertEquals("1234", resultAfterSet.toString());
		
	}
	
}
