package com.hp.opta.flex.functions;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ToUpperCaseTest {
	
	private ToUpperCase uc;

	@Before
	public void setUp() throws Exception {
		uc = new ToUpperCase();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void basicTest() {
		String data = new String("This is a long string");
		List<Object> objects = Arrays.asList(data);
		String result = (String)uc.apply(objects);
		
		Assert.assertEquals(data.toUpperCase(), result);
	}

}
