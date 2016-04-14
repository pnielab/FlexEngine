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

public class MainFlowTest {
	
	private ConfigurationMetaData conf;
	
	private static final int DATA_SIZE = 5;

	@Before
	public void setUp() throws Exception {
		
		conf = new ConfigurationMetaData();
		List<TokenMetaData> tokensList = createTokenMetaDataList(DATA_SIZE);
		conf.setTokenCount(tokensList.size());
		conf.setTokens(tokensList);
		conf.setParsingMethod(ParsingMethod.REGEX);
		conf.setParseString(createRegex(DATA_SIZE));
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		
		Object inputEvent = createInputEvent(DATA_SIZE);
		System.out.println("INPUT: " + String.valueOf(inputEvent));
		EventParsingDataHolder dh = new EventParsingDataHolder();
		dh.publish("main", conf);
		ParsingExcecutioner.publish("main", dh.get("main"));
		Object result = ParsingExcecutioner.runThroughFilter("main", inputEvent);
		
		System.out.println("OUTPUT: " + String.valueOf(result));

	}
	
	
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

	
	
	private List<TokenMetaData> createTokenMetaDataList(int size){
		List<TokenMetaData> list = new LinkedList<>();
		for(int i = 1; i <= size; i++){
			list.add(new TokenMetaData(
					String.join("-", "name",Integer.toString(i)), TokenType.String, "String", i));
		}
		return list;
	}
	
	
	private Object createInputEvent(int size){
		StringBuilder str = new StringBuilder();
		for(int i = 1; i <= size; i++){
			str.append(String.join("-", "value",Integer.toString(i))).append(",");
		}
		str.deleteCharAt(str.length()-1);
		return str.toString();
	}

}
