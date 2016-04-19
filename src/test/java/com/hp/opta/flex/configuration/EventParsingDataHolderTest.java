package com.hp.opta.flex.configuration;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hp.opta.flex.configuration.model.ConfigurationMetaData;

import mockit.Mock;
import mockit.MockUp;

public class EventParsingDataHolderTest {

	static MockUp mockup;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@BeforeClass
	public static void beforeClass(){
		mockup = new MockUp<EventParsingData>() {
			@Mock
			public void initialize(){
			}
		};
	}
	
	
	@AfterClass
	public static void afterClass(){
		mockup.tearDown();
	}
	
	

	@Test
	public void testBasic() {
		
		EventParsingDataHolder.publish("main", new ConfigurationMetaData(0));
		Assert.assertNotNull(EventParsingDataHolder.get("main"));
		EventParsingDataHolder.remove("main");
		Assert.assertNull(EventParsingDataHolder.get("main"));
	}

}
