package com.hp.opta.flex.functions;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OneOfTest {
	
	private OneOf oo;

	@Before
	public void setUp() throws Exception {
		oo = new OneOf();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void basicTest() {
		List<Object> objects = Arrays.asList("10", "20", "true", "false");
		String result = (String)oo.apply(objects);
		
		Assert.assertEquals("10", result);
		
		objects = Arrays.asList(null, "", "true", "false");
		result = (String)oo.apply(objects);
		
		Assert.assertEquals("true", result);		
	}

}
