package com.hp.opta.flex.functions.builder;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by zeev on 4/5/16.
 */
public interface FunctionBuilder {

    public Map<String, Function<List<Object>, Object>> prepare();

}
