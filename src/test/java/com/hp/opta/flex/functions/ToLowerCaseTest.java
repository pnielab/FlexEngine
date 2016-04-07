package com.hp.opta.flex.functions;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ToLowerCaseTest {

	private ToLowerCase lc;

	@Before
	public void setUp() throws Exception {
		lc = new ToLowerCase();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void basicTest() {
		String data = new String("This is a long string");
		List<Object> objects = Arrays.asList(data);
		String result = (String)lc.apply(objects);
		
		Assert.assertEquals(data.toLowerCase(), result);
	}

}
