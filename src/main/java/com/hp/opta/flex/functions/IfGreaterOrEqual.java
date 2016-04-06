package com.hp.opta.flex.functions;

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


    @Override
    public Object apply(List<Object> o) {
        return null;
    }
}
