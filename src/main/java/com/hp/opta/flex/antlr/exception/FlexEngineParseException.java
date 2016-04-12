package com.hp.opta.flex.antlr.exception;

/**
 * Created by zeev on 4/11/16.
 */
public class FlexEngineParseException extends RuntimeException {
    
    public FlexEngineParseException() {
    }

    public FlexEngineParseException(String message) {
        super(message);
    }

    public FlexEngineParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
