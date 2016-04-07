package com.hp.opta.flex.functions.builder.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.hp.opta.flex.functions.Concatenate;
import com.hp.opta.flex.functions.IfAorBThenElse;
import com.hp.opta.flex.functions.IfGreaterOrEqual;
import com.hp.opta.flex.functions.IfThenElse;
import com.hp.opta.flex.functions.LengthOperation;
import com.hp.opta.flex.functions.OneOf;
import com.hp.opta.flex.functions.SimpleMap;
import com.hp.opta.flex.functions.Split;
import com.hp.opta.flex.functions.StringConstant;
import com.hp.opta.flex.functions.ToLowerCase;
import com.hp.opta.flex.functions.ToUpperCase;
import com.hp.opta.flex.functions.UseCurrentYear;
import com.hp.opta.flex.functions.builder.FunctionBuilder;

/**
 * Created by zeev on 4/5/16.
 */
public class FunctionBuilderImpl implements FunctionBuilder {

    @Override
    public Map<String, Function<List<Object>, Object>> prepare() {
        Map<String, Function<List<Object>, Object>> functions = new HashMap<String, Function<List<Object>, Object>>();
        functions.put(Concatenate.FUNCTION_NAME.getFunctionName(), new Concatenate());
        functions.put(IfAorBThenElse.FUNCTION_NAME.getFunctionName(), new IfAorBThenElse());
        functions.put(IfGreaterOrEqual.FUNCTION_NAME.getFunctionName(), new IfGreaterOrEqual());
        functions.put(IfThenElse.FUNCTION_NAME.getFunctionName(), new IfThenElse());
        functions.put(UseCurrentYear.FUNCTION_NAME.getFunctionName(), new UseCurrentYear());
        functions.put(OneOf.FUNCTION_NAME.getFunctionName(), new OneOf());
        functions.put(LengthOperation.FUNCTION_NAME.getFunctionName(), new LengthOperation());
        functions.put(ToUpperCase.FUNCTION_NAME.getFunctionName(), new ToUpperCase());
        functions.put(ToLowerCase.FUNCTION_NAME.getFunctionName(), new ToLowerCase());
        functions.put(StringConstant.FUNCTION_NAME.getFunctionName(), new StringConstant());
        functions.put(SimpleMap.FUNCTION_NAME.getFunctionName(), new SimpleMap());
        functions.put(Split.FUNCTION_NAME.getFunctionName(), new Split());
        
        return functions;
    }
}
