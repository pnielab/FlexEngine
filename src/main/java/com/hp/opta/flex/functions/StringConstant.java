package com.hp.opta.flex.functions;

import java.util.List;
import java.util.function.Function;

import com.hp.opta.flex.functions.enums.FunctionName;

/**
 * This takes a single string literal parameter, and returns it.
 */
public class StringConstant implements Function<List<Object>, Object> {

    public static final FunctionName FUNCTION_NAME = FunctionName.StringConstant;

    @Override
    public Object apply(List<Object> objects) {
        return String.valueOf(objects.get(0));
    }
}
