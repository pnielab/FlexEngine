package com.hp.opta.flex.functions;

import java.util.List;
import java.util.function.Function;

import com.hp.opta.flex.functions.enums.FunctionName;

/**
 * There are 3 parameters. The first is the string to split (a string). 
 * The second is the delimiter (a literal string). The third is the index 
 * (a literal integer). If the delimiter or the index is blank or null, 
 * then the result is the same as the first argument. Otherwise the string 
 * to split is split around occurrences of the delimiter, with the index'th 
 * string returned. For example, if the parameters are 
 * “The string to split,” “ “ (space), and “2”, the result will be “string”.
 */
public class Split implements Function<List<Object>, Object> {

    public static final FunctionName FUNCTION_NAME = FunctionName.Split;
    
    @Override
    public Object apply(List<Object> objects) {

        String stringToSplit = (String)objects.get(0);
        String delimiter = (String)objects.get(1);
        Integer index = null;
        try{
        	index = Integer.valueOf(String.valueOf(objects.get(2)));
        }catch(NumberFormatException | NullPointerException exception){
        }
        
        if(delimiter != null && delimiter.length()>0 && index != null){
            return stringToSplit.split(delimiter)[index - 1];
        }
        
        return stringToSplit;
    }
}
