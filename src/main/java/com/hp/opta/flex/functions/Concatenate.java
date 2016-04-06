package com.hp.opta.flex.functions;

import org.apache.commons.lang3.StringUtils;

import java.util.function.Function;


/**
 * The parameters can be literal strings or
 * other values of various types. The result
 * is a string that consists of all of these
 * parameters concatenated together.
 * __concatenate("Active",protocol
 * ," Ports: ",portnum)
 * __concatenate("CompanyName: [",
 * CompanyName,"]")
 * __concatenate("PF:
 * ",PassOrBlock)
 *
 * @return String
 */
public class Concatenate implements Function<Object, Object> {

    public static final String FUNCTION_NAME = "__concatenate";

    @Override
    public Object apply(Object objectArray) {
        Object[] objects = (Object[]) objectArray;
        // objects.length == 0 should fail in validation
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < objects.length; i++) {
            if (objects[i] != null) {
                sb.append(String.valueOf(objects[i]));
            }
        }
        return StringUtils.isEmpty(sb.toString()) ? null : sb.toString();
    }
}
