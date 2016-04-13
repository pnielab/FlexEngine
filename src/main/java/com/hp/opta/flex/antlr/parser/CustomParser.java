package com.hp.opta.flex.antlr.parser;

import com.hp.opta.flex.configuration.model.ConfigurationMetaData;

/**
 * Created by zeev on 4/11/16.
 */
public interface CustomParser {

    public ConfigurationMetaData parse(String configFile);
}
