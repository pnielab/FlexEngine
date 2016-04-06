package com.hp.opta.flex.functions.builder.impl;

import com.hp.opta.flex.functions.Concatenate;
import com.hp.opta.flex.functions.IfAorBThenElse;
import com.hp.opta.flex.functions.builder.FunctionBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by zeev on 4/5/16.
 */
public class FunctionBuilderImpl implements FunctionBuilder {

    @Override
    public Map<String, Function<List<Object>, Object>> prepare() {
        Map<String, Function<List<Object>, Object>> functions = new HashMap<String, Function<List<Object>, Object>>();
        functions.put(Concatenate.FUNCTION_NAME.getFunctionName(), new Concatenate());
        functions.put(IfAorBThenElse.FUNCTION_NAME.getFunctionName(), new IfAorBThenElse());
        return functions;
    }
}
