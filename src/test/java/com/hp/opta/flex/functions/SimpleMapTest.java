package com.hp.opta.flex.functions;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SimpleMapTest {

	private SimpleMap sm;
	
	@Before
	public void setUp() throws Exception {
		sm = new SimpleMap();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void basicTest() {
		
		List<Object> objects = Arrays.asList("Jurgen","me=1","you=2","he=3","Jurgen=10");
		String result = (String)sm.apply(objects);
		
		Assert.assertEquals("10", result);
		
		objects = Arrays.asList("he","me-1","you-2","he-3","Jurgen-10","-");
		result = (String)sm.apply(objects);
		
		Assert.assertEquals("3", result);
		
		
	}

}
