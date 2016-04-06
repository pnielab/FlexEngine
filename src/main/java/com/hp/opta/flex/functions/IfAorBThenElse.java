package com.hp.opta.flex.functions;

import com.hp.opta.flex.functions.enums.FunctionName;
import org.apache.commons.lang3.StringUtils;

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
public class IfAorBThenElse implements Function<Object, Object> {

    public static final FunctionName FUNCTION_NAME = FunctionName.IfAorBThenElse;

    @Override
    public Object apply(Object objectArray) {

        String[] values = (String[]) objectArray;
        String compareWith = values[0];
        String parameterA = values[1];
        String ParameterB = values[2];
        String whenTrue = values[3];
        String whenFalse = values[4];
        if (!StringUtils.isEmpty(compareWith)) {
            return (compareWith.equals(parameterA) || compareWith.equals(ParameterB)) ? whenTrue : whenFalse;
        }
        return (compareWith == parameterA || compareWith == ParameterB) ? whenTrue : whenFalse;
    }
}
