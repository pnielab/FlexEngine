package com.hp.opta.flex.antlr.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zeev on 4/10/16.
 */
public class ConfigMetaData {


    private String regex;
    private int tokenCount;
    private List<FlexToken> tokens;


    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

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
}
