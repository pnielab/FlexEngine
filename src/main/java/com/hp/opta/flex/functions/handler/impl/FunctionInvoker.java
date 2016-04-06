package com.hp.opta.flex.functions.handler.impl;

import com.hp.opta.flex.functions.builder.impl.FunctionBuilderImpl;
import com.hp.opta.flex.functions.handler.Handler;
import org.slf4j.Logger;

import java.util.Map;
import java.util.function.Function;

/**
 * Created by zeev on 4/5/16.
 */
public class FunctionInvoker implements Handler {

    private static final Map<String, Function<Object, Object>> functions;
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(FunctionInvoker.class);

    static {
        functions = new FunctionBuilderImpl().prepare();
    }

    @Override
    public Object handle(String functionName, Object params) {
        Function<Object, Object> function = functions.get(functionName);
        if (function == null) {
            logger.warn("function: {}, is not supported", functionName);
            return null;
        }
        return function.apply(params);
    }
}
