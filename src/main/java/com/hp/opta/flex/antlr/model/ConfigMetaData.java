package com.hp.opta.flex.antlr.model;

import java.util.List;

/**
 * Created by zeev on 4/10/16.
 */
public class ConfigMetaData {


    private ParsingMethod parsingMethod;
    private String parseString;
    private int tokenCount;
    private List<FlexToken> tokens;

    public int getTokenCount() {
        return tokenCount;
    }

    public void setTokenCount(int tokenCount) {
        this.tokenCount = tokenCount;
    }

    public List<FlexToken> getTokens() {
        return tokens;
    }

    public void setTokens(List<FlexToken> tokens) {
        this.tokens = tokens;
    }

    public ParsingMethod getParsingMethod() {
        return parsingMethod;
    }

    public void setParsingMethod(ParsingMethod parsingMethod) {
        this.parsingMethod = parsingMethod;
    }

    public String getParseString() {
        return parseString;
    }

    public void setParseString(String parseString) {
        this.parseString = parseString;
    }
}
