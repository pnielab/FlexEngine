package com.hp.opta.flex.functions;

import java.util.List;
import java.util.function.Function;

import com.hp.opta.flex.functions.enums.FunctionName;

/**
 * The parameter is a single string, which is converted to uppercase and returned.
 */
public class ToUpperCase implements Function<List<Object>, Object> {

    public static final FunctionName FUNCTION_NAME = FunctionName.ToUpperCase;

    @Override
    public Object apply(List<Object> objects) {
        String parameter = String.valueOf(objects.get(0));
        return parameter.toUpperCase();
    }
}
