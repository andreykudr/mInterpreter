package org.kudryashov.comliper.elements.elementTypes.util;

import org.kudryashov.comliper.elements.elementTypes.ElementName;
import org.kudryashov.comliper.elements.elementTypes.ProgramElement;
import org.kudryashov.comliper.elements.elementTypes.operator.UnaryOperator;
import org.kudryashov.comliper.elements.elementTypes.word.type.VariableType;

import java.util.ArrayList;
import java.util.List;

public class Multiplier implements TypedProgramElement {

    private UnaryOperator unaryOperator;
    private TypedProgramElement parameter;

    public Multiplier(TypedProgramElement entity) {
        this.parameter = entity;
    }

    public Multiplier(UnaryOperator unaryOperator, Multiplier multiplier) {
        this.unaryOperator = unaryOperator;
        this.parameter = multiplier;
    }

    @Override
    public List<ElementName> toPoliz() {
        if (unaryOperator == null) {
            return parameter.toPoliz();
        } else {
            ArrayList<ElementName> poliz = new ArrayList<>();
            poliz.addAll(parameter.toPoliz());
            poliz.addAll(unaryOperator.toPoliz());
            return poliz;
        }
    }

    @Override
    public VariableType type() {
        if (unaryOperator != null) {
            return getOperationType();
        } else {
            return parameter.type();
        }
    }

    private VariableType getOperationType() {
        VariableType type = parameter.type();
        if (unaryOperator.operandTypes().contains(type)) {
            return unaryOperator.resultType(type);
        } else {
            throw new TypeError(type + "not correct");
        }
    }
}
