package com.hp.opta.flex.main;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MainTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRunProgram() throws URISyntaxException, IOException {
		
		Main main = new Main();
		String [] args = new String[6];
		args[0] = "-c";
		args[1] = Paths.get(ClassLoader.getSystemResource("com/hp/opta/flex/main/sample-simple-conf.properties").toURI()).toString();
		args[2] = "-i";
		args[3] = Paths.get(ClassLoader.getSystemResource("com/hp/opta/flex/main/sample-input.log").toURI()).toString();
		args[4] = "-o";
		
		File output = File.createTempFile("output.", "out");
		
		System.out.println(output.getAbsolutePath());
		
		args[5] =output.getAbsolutePath();
				
		main.runProgram(args);
		
		String result = new String("{third=is, first=This-is-sample-input, second=This, fourth=sample}");
		
		StringBuilder strBuilder = new StringBuilder();
    	
    	Files.readAllLines(Paths.get(output.toURI())).forEach((line) -> strBuilder.append(line));
    	
    	Assert.assertEquals(result, strBuilder.toString());
		
	}
	
	
	@Test
	public void testNegativeInput() throws URISyntaxException, IOException {
		
		Main main = new Main();
		String [] args = new String[2];
		args[0] = "-c";
		args[1] = Paths.get(ClassLoader.getSystemResource("com/hp/opta/flex/main/sample-simple-conf.properties").toURI()).toString();
				
		main.runProgram(args);
		
	}
	

}
