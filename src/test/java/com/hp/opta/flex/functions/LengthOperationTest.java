package com.hp.opta.flex.functions;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LengthOperationTest {
	
	private LengthOperation lo;

	@Before
	public void setUp() throws Exception {
		lo = new LengthOperation();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void basicTest() {
		String data = new String("This is a long string");
		List<Object> objects = Arrays.asList(data);
		Integer result = (Integer)lo.apply(objects);
		
		Assert.assertEquals(new Integer(data.length()), result);
	}

}
