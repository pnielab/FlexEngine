package com.hp.opta.flex.antlr.parser;

import com.hp.opta.flex.antlr.model.ConfigMetaData;

/**
 * Created by zeev on 4/11/16.
 */
public interface CustomParser {

    public ConfigMetaData parse(String configFile); }
