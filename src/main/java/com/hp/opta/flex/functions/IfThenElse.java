package com.hp.opta.flex.functions;

import com.hp.opta.flex.functions.enums.FunctionName;

import java.util.List;
import java.util.function.Function;

/**
 * There are four parameters. Each can be
 * either a literal string or a regular string
 * (although other types are converted to
 * strings). The first two parameters are
 * compared, and if they are equal, then the
 * third parameter is returned as the result.
 * Otherwise (if the first two parameters
 * differ), the fourth parameter is returned.
 */
public class IfThenElse implements Function<List<Object>, Object> {

    public static final FunctionName FUNCTION_NAME = FunctionName.IfThenElse;

    @Override
    public Object apply(List<Object> objects) {
        String firstParameter = String.valueOf(objects.get(0));
        String secondParameter = String.valueOf(objects.get(1));
        return firstParameter.equals(secondParameter) ? objects.get(2) : objects.get(3);
    }
}
