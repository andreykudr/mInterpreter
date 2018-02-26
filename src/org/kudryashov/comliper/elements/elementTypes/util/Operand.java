package org.kudryashov.comliper.elements.elementTypes.util;

import org.kudryashov.comliper.elements.elementTypes.ElementName;
import org.kudryashov.comliper.elements.elementTypes.operator.BinaryOperator;
import org.kudryashov.comliper.elements.elementTypes.word.type.VariableType;
import org.kudryashov.comliper.syntax.exception.SyntaxError;

import java.util.ArrayList;
import java.util.List;

public class Operand implements TypedProgramElement {

    private final Composed composed1;
    private final BinaryOperator operator;
    private final Composed composed2;

    public Operand(Composed composed1, BinaryOperator operator, Composed composed2) {
        if (composed1 == null || operator != null && composed2 == null) {
            throw new SyntaxError(this.toString());
        }
        this.composed1 = composed1;
        this.operator = operator;
        this.composed2 = composed2;
    }

    @Override
    public VariableType type() {
        if (operator == null) {
            return composed1.type();
        }
        VariableType left = composed1.type();
        VariableType right = composed2.type();
        if (operator.leftOperandTypes().contains(left) && operator.rightOperandTypes().contains(right)) {
            return operator.resultType(left, right);
        }
        throw new TypeError(left + ", " + right + operator);
    }

    @Override
    public List<ElementName> toPoliz() {
        if (operator == null) {
            return composed1.toPoliz();
        } else {
            ArrayList<ElementName> poliz = new ArrayList<>();
            poliz.addAll(composed1.toPoliz());
            poliz.addAll(composed2.toPoliz());
            poliz.addAll(operator.toPoliz());
            return poliz;
        }
    }
}
