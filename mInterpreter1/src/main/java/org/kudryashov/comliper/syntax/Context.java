package org.kudryashov.comliper.syntax;

import org.kudryashov.comliper.elements.elementTypes.ElementName;
import org.kudryashov.comliper.elements.elementTypes.ProgramElement;
import org.kudryashov.comliper.elements.elementTypes.word.type.VariableType;
import org.kudryashov.comliper.semantyc.expression.SemanticExpressionAnalyzer;

import java.util.ArrayList;
import java.util.List;

public class Context {

    private List<ElementName> lexemes;
    private List<ProgramElement> elements = new ArrayList<>();
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

    public void put(ProgramElement element) {
        elements.add(current, element);
    }

    public boolean empty() {
        return elements.size() > current;
    }

    public void reset() {
        current = previous;
    }

    public void commit() {
        previous = current;
    }
}
