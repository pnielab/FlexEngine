package com.hp.opta.flex.antlr.exception;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.BitSet;

/**
 * Created by zeev on 4/11/16.
 */
public class ErrorListener implements ANTLRErrorListener {

    private static final Logger logger = LoggerFactory.getLogger(ErrorListener.class);
    private String errorMessage;
    private RecognitionException exception;

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object o, int i, int i1, String s, RecognitionException e) {
        logger.error("syntax error while parsing grammer file with error message: {}", e.getMessage(), e);
        errorMessage = e.getMessage();
    }

    @Override
    public void reportAmbiguity(Parser parser, DFA dfa, int i, int i1, boolean b, BitSet bitSet, ATNConfigSet atnConfigSet) {

    }

    @Override
    public void reportAttemptingFullContext(Parser parser, DFA dfa, int i, int i1, BitSet bitSet, ATNConfigSet atnConfigSet) {

    }

    @Override
    public void reportContextSensitivity(Parser parser, DFA dfa, int i, int i1, int i2, ATNConfigSet atnConfigSet) {

    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public RecognitionException getException() {
        return exception;
    }
}
