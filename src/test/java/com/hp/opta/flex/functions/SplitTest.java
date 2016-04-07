package com.hp.opta.flex.functions;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SplitTest {
	
	private Split s;

	@Before
	public void setUp() throws Exception {
		s = new Split();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void basicTest() {
		List<Object> objects = Arrays.asList("This is a long string", " ", 2);
		String result = (String)s.apply(objects);
		
		Assert.assertEquals("is", result);
	}

}
