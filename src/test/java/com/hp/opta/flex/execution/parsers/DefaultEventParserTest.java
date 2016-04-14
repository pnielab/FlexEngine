package com.hp.opta.flex.execution.parsers;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.hp.opta.flex.configuration.EventParsingData;
import com.hp.opta.flex.configuration.model.ConfigurationMetaData;

public class DefaultEventParserTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBasic() {
		DefaultEventParser<String,String> p = new DefaultEventParser<>();
		
		p.setParsingData(new EventParsingData(new ConfigurationMetaData()));
		
		Assert.assertNotNull(p.getParsingData());
		
		p = new DefaultEventParser<>(new EventParsingData(new ConfigurationMetaData()));
		
		Assert.assertNotNull(p.getParsingData());

		
	}

}
