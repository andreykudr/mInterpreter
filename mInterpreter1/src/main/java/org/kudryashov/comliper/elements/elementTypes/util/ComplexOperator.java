package org.kudryashov.comliper.elements.elementTypes.util;

import org.kudryashov.comliper.elements.elementTypes.ElementName;
import org.kudryashov.comliper.elements.elementTypes.ProgramElement;
import org.kudryashov.comliper.elements.elementTypes.operator.Operator;

import java.util.ArrayList;
import java.util.List;

public class ComplexOperator implements ProgramElement, Operator {

    private List<Operator> operators;

    public ComplexOperator(List<Operator> operators) {
        this.operators = operators;
    }

    @Override
    public Dimension dimension() {
        return Dimension.MULTIPLE;
    }

    @Override
    public List<ElementName> toPoliz() {
        ArrayList<ElementName> poliz = new ArrayList<>();
        for (Operator operator : operators) {
            poliz.addAll(operator.toPoliz());
        }
        return poliz;
    }

    public boolean isEmpty() {
        return operators.isEmpty();
    }
}
