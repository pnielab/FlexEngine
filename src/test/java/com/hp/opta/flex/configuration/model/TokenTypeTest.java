package com.hp.opta.flex.configuration.model;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

public class TokenTypeTest {
	
	private static List<String> validValues;

	@BeforeClass
	public static void setUp() throws Exception {
		validValues = Arrays.asList(
				"String", 
				"Integer", 
				"Long", 
				"TimeStamp", 
				"IPAddress");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBasic() {
		for(String str : validValues){
			TokenType.valueOf(str);
		}
	}

}
