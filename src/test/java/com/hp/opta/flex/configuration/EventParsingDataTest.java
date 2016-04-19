package com.hp.opta.flex.configuration;

import java.util.List;
import java.util.regex.Pattern;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.hp.opta.flex.configuration.model.ConfigurationMetaData;
import com.hp.opta.flex.configuration.model.ParsingMethod;
import com.hp.opta.flex.configuration.model.TokenMetaData;

public class EventParsingDataTest {

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBasic1() {

		ConfigurationMetaData conf = new ConfigurationMetaData(0);
		EventParsingData epd = new EventParsingData(conf);
		
		exception.expect(IllegalStateException.class);
		exception.expectMessage("Could not initialize Parsing Pattern. Parsing String is empty");
		epd.initialize();
		
	}
	
	@Test
	public void testBasic2() {

		ConfigurationMetaData conf = new ConfigurationMetaData(0);
		conf.setParseString(".*");
		
		EventParsingData epd = new EventParsingData(conf);
		
		exception.expect(IllegalStateException.class);
		exception.expectMessage("Could not initialize Parsing Pattern. Parsing Method does not exist");
		epd.initialize();
		
	}
	
	

	@Test
	public void testBasic3() {

		ConfigurationMetaData conf = new ConfigurationMetaData(0);
		conf.setParseString(".*");
		conf.setParsingMethod(ParsingMethod.REGEX);
		EventParsingData epd = new EventParsingData(conf);
		
		epd.initialize();
		
	}
	
	
	@Test
	public void testBasic4() {

		EventParsingData epd = new EventParsingData(new ConfigurationMetaData(0));
		Assert.assertNotNull(epd.getConfigurationMetaData());
		
		epd.setParsePattern(Pattern.compile(""));
		
		Assert.assertNotNull(epd.getParsePattern());
		
		epd.setConfigurationMetaData(null);
		Assert.assertNull(epd.getConfigurationMetaData());
		
		epd.setConfigurationMetaData(new ConfigurationMetaData(0));
		Assert.assertNotNull(epd.getConfigurationMetaData());
		
		
	}	


}
