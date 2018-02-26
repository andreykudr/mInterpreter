package org.kudryashov.comliper.syntax.exception;

public class SyntaxError extends RuntimeException {
    public SyntaxError(String s) {
        super(s);
    }

    public SyntaxError() {
        super();
    }
}
