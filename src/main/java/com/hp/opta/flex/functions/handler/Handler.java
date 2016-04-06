package com.hp.opta.flex.functions.handler;

import java.util.List;

/**
 * Created by zeev on 4/5/16.
 */
public interface Handler {

    public Object handle(String functionName, List<Object> params);

}
