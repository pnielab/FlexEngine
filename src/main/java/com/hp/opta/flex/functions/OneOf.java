package com.hp.opta.flex.functions;

import java.util.List;
import java.util.function.Function;

import com.hp.opta.flex.functions.enums.FunctionName;

/**
 * This operation takes an arbitrary number of parameters. 
 * Each can be either a literal string or a regular string. 
 * The first one that is not null and not zero-length is returned.
 */
public class OneOf implements Function<List<Object>, Object> {

    public static final FunctionName FUNCTION_NAME = FunctionName.OneOf;

    @Override
    public Object apply(List<Object> objects) {
    	
    	for(Object obj : objects){
    		if((obj != null) && (obj.toString().length() > 0)){
    			return obj;
    		}
    	}
        return null;
    }
}
