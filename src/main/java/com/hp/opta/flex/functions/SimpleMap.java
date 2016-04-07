package com.hp.opta.flex.functions;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import com.hp.opta.flex.functions.enums.FunctionName;

/**
 * There are n+1 or n+2 parameters. The first parameter is a string which is to be
 * looked up in the map. The next n parameters are the map, in the form of
 * string literals each of which has a key, an equals sign, and a value. If the key
 * matches the first parameter, then the value for that key is returned. If the final
 * parameter is a single character, it is used as the delimiter instead of the equals
 * sign. For example, if the parameters are (all literal except the first): “Foo”,
 * “Bar=17”, “Foo=34”, then the returned value will be “34”. If no key matches, null 
 * is returned.
 * __simpleMap(FileInfected,"0=No","1=Yes","=")
 * __simpleMap(Type,"8=Success","16=Failure")
 */
public class SimpleMap implements Function<List<Object>, Object> {

    public static final FunctionName FUNCTION_NAME = FunctionName.SimpleMap;
    
    private static final String DEFAULT_DELIMITER = "=";

    private String searchString;
    
    @Override
    public Object apply(List<Object> objects) {
    	
    	Iterator<Object> objectItr = objects.iterator();
    	searchString = String.valueOf(objectItr.next());
    	
    	String lastParam = String.valueOf(objects.get(objects.size() - 1));
    	
    	if(lastParam.length() == 1){
    		searchString += lastParam;
    	}else{
    		searchString += DEFAULT_DELIMITER;
    	}
    	
    	while(objectItr.hasNext()){
    		String testVal = (String) objectItr.next();
    		if(testVal.startsWith(searchString)){
    			return testVal.substring(searchString.length());
    		}
    	}
    	
        return null;
    }
}
