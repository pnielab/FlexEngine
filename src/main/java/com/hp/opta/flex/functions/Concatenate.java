package com.hp.opta.flex.functions;

import com.hp.opta.flex.functions.enums.FunctionName;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
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
public class Concatenate implements Function<List<Object>, Object> {

    public static final FunctionName FUNCTION_NAME = FunctionName.Concatenate;


    @Override
    public Object apply(List<Object> objects) {
        // objects.size == 0 should fail in validation
        StringBuilder sb = new StringBuilder();
        objects.stream().forEach(object -> {
            if (object != null) {
                sb.append(toString().valueOf(object));
            }
        });
        return StringUtils.isEmpty(sb.toString()) ? null : sb.toString();
    }
}
