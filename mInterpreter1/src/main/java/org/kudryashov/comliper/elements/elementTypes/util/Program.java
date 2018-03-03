package org.kudryashov.comliper.elements.elementTypes.util;

import org.kudryashov.comliper.elements.elementTypes.ElementName;
import org.kudryashov.comliper.elements.elementTypes.ProgramElement;

import java.util.List;

public class Program implements ProgramElement {

    private final Definitions definitions;
    private final ComplexOperator operators;

    public Program(Definitions definitions, ComplexOperator operators) {

        this.definitions = definitions;
        this.operators = operators;
    }

    @Override
    public List<ElementName> toPoliz() {
        return operators.toPoliz();
    }
}
