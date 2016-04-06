package com.hp.opta.flex.functions;

import java.util.function.Function;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IfAorBThenElseTest {

	Function f = null;
	
	@Before
	public void setUp() throws Exception {
		f = new IfAorBThenElse();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		
		String [] objectArray = new String[]{
				"test1","test2","test3","true","false"};
		
		Object obj = f.apply(objectArray);
		Assert.assertEquals(obj, "false");
		
		objectArray = new String[]{
				"test1","test1","test3","true","false"};

		obj = f.apply(objectArray);
		Assert.assertEquals(obj, "true");

		objectArray = new String[]{
				"test1","test2","test1","true","false"};

		obj = f.apply(objectArray);
		Assert.assertEquals(obj, "true");

	}

}
