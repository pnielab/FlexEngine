package com.hp.opta.flex.functions;

import com.hp.opta.flex.functions.enums.FunctionName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.Function;

/**
 * There are four parameters. If either
 * of the first two parameters is null, null is
 * returned and an error is logged.
 * Otherwise, those two parameters are
 * parsed as integers and compared. Any
 * parsing errors treat the value as zero. If
 * the first parameter is numerically larger
 * than the second, then the third
 * parameter is returned. Otherwise, the
 * fourth parameter is returned.
 */
public class IfGreaterOrEqual implements Function<List<Object>, Object> {

    public static final FunctionName FUNCTION_NAME = FunctionName.IfGreaterOrEqual;
    private static final Logger logger = LoggerFactory.getLogger(IfGreaterOrEqual.class);

    @Override
    public Object apply(List<Object> objects) {

        if (objects.get(0) == null || objects.get(1) == null) {
            logger.error("either one of the first two parameters is null, first: {}, second: {}", objects.get(0), objects.get(1));
            return null;
        }
        Integer firstParameter = parse(objects.get(0));
        Integer secondParameter = parse(objects.get(1));
        return firstParameter.compareTo(secondParameter) > 0 ? objects.get(2) : objects.get(3);
    }

    private Integer parse(Object value) {
        Integer result = 0;
        try {
            if (value instanceof Integer) {
                result = (Integer) value;
            } else if (value instanceof String) {
                result = Integer.valueOf((String) value);
            } else if (value instanceof Double) {
                result = ((Double) value).intValue();
            } else if (value instanceof Long) {
                result = ((Long) value).intValue();
            }

        } catch (Exception e) {
            logger.error("unable to parse integer from: {}, returning 0 instead", value);
        }
        return result;
    }
}


