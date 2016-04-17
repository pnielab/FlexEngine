package com.hp.opta.flex.antlr.utils;

import com.hp.opta.flex.configuration.model.ConfigurationMetaData;

/**
 * Created by zeev on 4/17/16.
 */
public class ParserUtils {

    public static String getAnyString(String text) {
        return (text == null || text.isEmpty()) ? text : text.substring(1, text.length() - 1);
    }

    public static ConfigurationMetaData createConfigurationMetaDataIfNull(ConfigurationMetaData configurationMetaData, int tokenCount) {
        return configurationMetaData != null ? configurationMetaData : new ConfigurationMetaData(tokenCount);
    }
}
