package com.hp.opta.flex.functions;

import com.hp.opta.flex.functions.enums.FunctionName;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.function.Function;

/**
 * There are five parameters. Each can be
 * either a literal string or a regular string
 * (although other types are converted to
 * strings). If the first parameter is equal to
 * the second or the first parameter is equal
 * to the third parameter, then the fourth
 * parameter is returned. Otherwise, the
 * fifth parameter is returned.
 */
public class IfAorBThenElse implements Function<List<Object>, Object> {

    public static final FunctionName FUNCTION_NAME = FunctionName.IfAorBThenElse;

    @Override
    public Object apply(List<Object> objects) {

        String compareWith = String.valueOf(objects.get(0));
        String parameterA = String.valueOf(objects.get(1));
        String ParameterB = String.valueOf(objects.get(2));
        String whenTrue = String.valueOf(objects.get(3));
        String whenFalse = String.valueOf(objects.get(4));
        if (!StringUtils.isEmpty(compareWith)) {
            return (compareWith.equals(parameterA) || compareWith.equals(ParameterB)) ? whenTrue : whenFalse;
        }
        return (compareWith == parameterA || compareWith == ParameterB) ? whenTrue : whenFalse;
    }
}
