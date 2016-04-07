package com.hp.opta.flex.functions;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IfThenElseTest {

	private IfThenElse ie = null;
	@Before
	public void setUp() throws Exception {
		ie = new IfThenElse();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void basicTest() {
		
		List<Object> objects = Arrays.asList("10", "10", "true", "false");
		
		String result = (String) ie.apply(objects);
		Assert.assertEquals("true", result);
		
		objects = Arrays.asList("20", "10", "true", "false");
		result = (String) ie.apply(objects);
		Assert.assertEquals("false", result);
		
		objects = Arrays.asList(null, "10", "true", "false");
		result = (String) ie.apply(objects);
		Assert.assertEquals("false", result);
	}

}
