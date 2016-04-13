package com.hp.opta.flex.antlr.parser.impl;

import com.hp.opta.flex.antlr.exception.ErrorListener;
import com.hp.opta.flex.antlr.exception.FlexEngineParseException;
import com.hp.opta.flex.antlr.model.ConfigMetaData;
import com.hp.opta.flex.antlr.parser.CustomParser;
import com.hp.opta.flex.antlr.parser.FlexGrammarLexer;
import com.hp.opta.flex.antlr.parser.FlexGrammarParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringReader;

/**
 * Created by zeev on 4/11/16.
 */
public class ParserImpl implements CustomParser {

    private static final Logger logger = LoggerFactory.getLogger(ParserImpl.class);

    @Override
    public ConfigMetaData parse(String configFile) {

        if (!StringUtils.isEmpty(configFile)) {
            try {
                CharStream input = new ANTLRInputStream(new StringReader(configFile));
                FlexGrammarLexer lex = new FlexGrammarLexer(input);
                CommonTokenStream tokens = new CommonTokenStream(lex);
                FlexGrammarParser parser = new FlexGrammarParser(tokens);
                parser.removeErrorListeners();
                ErrorListener errorListener = new ErrorListener();
                parser.addErrorListener(errorListener);
                ConfigMetaData configMetaData = resolve(parser);
                validate(configMetaData);
                return configMetaData;
            } catch (FlexEngineParseException e) {
                throw e;
            } catch (Exception e) {
                logger.error("unable to parse config file, error: {}", e.getMessage(), e);
                throw new FlexEngineParseException("unable to parse config file", e);
            }
        } else {
            logger.error("config file is null or empty");
            throw new IllegalArgumentException("config file is null or empty");
        }

    }

    private void validate(ConfigMetaData configMetaData) {
        if (configMetaData.getTokenCount() != configMetaData.getTokens().size()) {
            throw new IllegalArgumentException("token count mismatch to number of tokens");
        }
    }

    private ConfigMetaData resolve(FlexGrammarParser parser) {
        FlexGrammarParser.ParseConfigFileContext ctx = parser.parseConfigFile();
        if (parser.getNumberOfSyntaxErrors() > 0) {
            ErrorListener errorListener = (ErrorListener) parser.getErrorListeners().get(0);
            logger.error("syntax error when parsing tokens: ", errorListener.getErrorMessage(), errorListener.getException());
            throw new FlexEngineParseException("syntax error when parsing tokens", errorListener.getException());
        }
        return ctx.parseResponse;
    }
}
