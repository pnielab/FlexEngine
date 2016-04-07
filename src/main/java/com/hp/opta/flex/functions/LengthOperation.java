package com.hp.opta.flex.functions;

import java.util.List;
import java.util.function.Function;

import com.hp.opta.flex.functions.enums.FunctionName;

/**
 * This operation retrieves the length of the operand string.
 */
public class LengthOperation implements Function<List<Object>, Object> {

    public static final FunctionName FUNCTION_NAME = FunctionName.LengthOperation;

    @Override
    public Object apply(List<Object> objects) {
        String parameter = String.valueOf(objects.get(0));
        return parameter.length();
    }
}
