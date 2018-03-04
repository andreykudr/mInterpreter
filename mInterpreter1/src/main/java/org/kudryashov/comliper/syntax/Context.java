package org.kudryashov.comliper.syntax;

import org.kudryashov.comliper.elements.elementTypes.ElementName;

import java.util.List;

public class Context {

    private List<ElementName> lexemes;
    private int current = 0;
    private int previous = 0;

    public Context(List<ElementName> lexemes) {
        this.lexemes = lexemes;
    }

    public ElementName peek() {
        return lexemes.get(current);
    }

    public ElementName pop() {
        return lexemes.get(current++);
    }

    public void reset() {
        current = previous;
    }

    public void commit() {
        previous = current;
    }
}
