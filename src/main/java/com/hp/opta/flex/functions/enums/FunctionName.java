package com.hp.opta.flex.functions.enums;

/**
 * Created by zeev on 4/6/16.
 */
public enum FunctionName {


    Concatenate("__concatenate"),
    IfAorBThenElse("__ifAorBThenElse");


    private String name;

    FunctionName(String name) {
        this.name = name;
    }

    public String getFunctionName() {
        return name;
    }

}
