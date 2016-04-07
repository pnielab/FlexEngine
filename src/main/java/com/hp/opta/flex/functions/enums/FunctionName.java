package com.hp.opta.flex.functions.enums;

/**
 * Created by zeev on 4/6/16.
 */
public enum FunctionName {

    Concatenate("__concatenate"),
    IfAorBThenElse("__ifAorBThenElse"),
    IfGreaterOrEqual("__ifGreaterOrEqual"),
    IfThenElse("__ifThenElse"),
    OneOf("__oneOf"),
    UseCurrentYear("__useCurrentYear"),
    LengthOperation("__lengthOperation"),
    ToUpperCase("__toUpperCase"),
    ToLowerCase("_toLowerCase"),
    StringConstant("__stringConstant"),
    SimpleMap("__simpleMap"),
    Split("__split");


    private String name;

    FunctionName(String name) {
        this.name = name;
    }

    public String getFunctionName() {
        return name;
    }

}
