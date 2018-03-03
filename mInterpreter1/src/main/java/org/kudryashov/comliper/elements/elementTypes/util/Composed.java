package org.kudryashov.comliper.elements.elementTypes.util;

import org.kudryashov.comliper.elements.elementTypes.ElementName;
import org.kudryashov.comliper.elements.elementTypes.ProgramElement;
import org.kudryashov.comliper.elements.elementTypes.operator.BinaryOperator;
import org.kudryashov.comliper.elements.elementTypes.word.type.VariableType;

import java.util.ArrayList;
import java.util.List;

public class Composed implements TypedProgramElement {

    private final Multiplier multiplier1;
    private final BinaryOperator operator;
    private final Multiplier multiplier2;

    public Composed(Multiplier multiplier1, BinaryOperator operator, Multiplier multiplier2) {
        if (multiplier1 == null || (operator != null && multiplier2 == null)) {
            throw new IllegalArgumentException();
        }
        this.multiplier1 = multiplier1;
        this.operator = operator;
        this.multiplier2 = multiplier2;
    }

    public Composed(Multiplier multiplier1) {
        this.multiplier1 = multiplier1;
        operator = null;
        multiplier2 = null;
    }

    @Override
    public List<ElementName> toPoliz() {
        if (operator == null) {
            return multiplier1.toPoliz();
        } else {
            ArrayList<ElementName> poliz = new ArrayList<>();
            poliz.addAll(multiplier1.toPoliz());
            poliz.addAll(multiplier2.toPoliz());
            poliz.addAll(operator.toPoliz());
            return poliz;
        }
    }


    @Override
    public VariableType type() {
        if (operator == null) {
            return multiplier1.type();
        }
        VariableType type1 = multiplier1.type();
        VariableType type2 = multiplier2.type();
        if (operator.leftOperandTypes().contains(type1) && operator.rightOperandTypes().contains(type2)) {
            return operator.resultType(type1, type2);
        } else {
            throw new TypeError(type1 + ", " + type2 + " is incorrect for operator " + operator);
        }
    }
}
