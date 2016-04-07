package com.hp.opta.flex.functions;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ConcatenateTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void basicTest() {
		String [] data = new String[]{"This", "is", "a", "test"};
		List<Object> objects = Arrays.asList(data);
		Concatenate c = new Concatenate();
		String result = (String)c.apply(objects);

		Assert.assertEquals(String.join("", data), result);
	}

}
