package com.hp.opta.flex.functions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UseCurrentYearTest {

	private UseCurrentYear cy;
	private final static DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME;
	
	@Before
	public void setUp() throws Exception {
		cy = new UseCurrentYear();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {

		String data = LocalDateTime.now().withYear(2000).format(dtf);
    	
		List<Object> objects = Arrays.asList(data);
    	
    	String result = (String)cy.apply(objects);
    	
    	LocalDateTime ts = LocalDateTime.parse(data);
    	ts = ts.withYear(Calendar.getInstance().get(Calendar.YEAR));
    			
		Assert.assertEquals(ts.format(dtf), result);
	}

}
