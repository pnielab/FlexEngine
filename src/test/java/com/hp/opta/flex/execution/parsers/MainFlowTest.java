package com.hp.opta.flex.execution.parsers;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hp.opta.flex.configuration.EventParsingDataHolder;
import com.hp.opta.flex.configuration.model.ConfigurationMetaData;
import com.hp.opta.flex.configuration.model.ParsingMethod;
import com.hp.opta.flex.configuration.model.TokenMetaData;
import com.hp.opta.flex.configuration.model.TokenType;
import com.hp.opta.flex.execution.ParsingExcecutioner;

/**
 * The Class MainFlowTest.
 *
 * @author Yoav Nordmann
 * @since Apr 17, 2016
 */
public class MainFlowTest {
	
	/** The conf. */
	private ConfigurationMetaData conf;
	
	/** The Constant DATA_SIZE. */
	private static final int DATA_SIZE = 5;

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		
		conf = new ConfigurationMetaData();
		List<TokenMetaData> tokensList = createTokenMetaDataList(DATA_SIZE);
		conf.setTokenCount(tokensList.size());
		tokensList.forEach((tmd) -> conf.addToken(tmd));
		conf.setParsingMethod(ParsingMethod.REGEX);
		conf.setParseString(createRegex(DATA_SIZE));
		
	}

	/**
	 * Tear down.
	 *
	 * @throws Exception the exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test.
	 */
	@Test
	public void test() {
		
		Object inputEvent = createInputEvent(DATA_SIZE);
		System.out.println("INPUT: " + String.valueOf(inputEvent));
		EventParsingDataHolder.publish("main", conf);
		ParsingExcecutioner.publish("main", EventParsingDataHolder.get("main"));
		Object result = ParsingExcecutioner.parse("main", inputEvent);
		
		System.out.println("OUTPUT: " + String.valueOf(result));

	}
	
	
	/**
	 * Creates the regex.
	 *
	 * @param size the size
	 * @return the string
	 */
	private String createRegex(int size){
		StringBuilder str = new StringBuilder();
		for(int i = 1; i <= size; i++){
			if(i == size){
				str.append("(.*)");
			}else{
				str.append("(.*?)").append(",");
			}
		}
		return str.toString();
	}

	
	
	/**
	 * Creates the token meta data list.
	 *
	 * @param size the size
	 * @return the list
	 */
	private List<TokenMetaData> createTokenMetaDataList(int size){
		List<TokenMetaData> list = new LinkedList<>();
		for(int i = 1; i <= size; i++){
			list.add(new TokenMetaData(
					String.join("-", "name",Integer.toString(i)), TokenType.String, "String", i));
		}
		return list;
	}
	
	
	/**
	 * Creates the input event.
	 *
	 * @param size the size
	 * @return the object
	 */
	private Object createInputEvent(int size){
		StringBuilder str = new StringBuilder();
		for(int i = 1; i <= size; i++){
			str.append(String.join("-", "value",Integer.toString(i))).append(",");
		}
		str.deleteCharAt(str.length()-1);
		return str.toString();
	}

}
