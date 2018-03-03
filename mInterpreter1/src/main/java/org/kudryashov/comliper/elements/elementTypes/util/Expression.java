package org.kudryashov.comliper.elements.elementTypes.util;

import org.kudryashov.comliper.elements.elementTypes.ElementName;
import org.kudryashov.comliper.elements.elementTypes.ProgramElement;
import org.kudryashov.comliper.elements.elementTypes.operator.BinaryOperator;
import org.kudryashov.comliper.elements.elementTypes.word.type.VariableType;
import org.kudryashov.comliper.syntax.exception.SyntaxError;

import java.util.ArrayList;
import java.util.List;

public class Expression implements TypedProgramElement {

    private final Operand operand1;
    private final BinaryOperator operator;
    private final Operand operand2;

    public Expression(Operand operand1) {
        this.operand1 = operand1;
        operator = null;
        operand2 = null;
    }

    public Expression(Operand operand1, BinaryOperator operator, Operand operand2) {
        if (operand1 == null || operator != null && operand2 == null) {
            throw new SyntaxError(this.toString());
        }
        this.operand1 = operand1;
        this.operator = operator;
        this.operand2 = operand2;
    }

    @Override
    public List<ElementName> toPoliz() {
        if (operator != null) {
            ArrayList<ElementName> poliz = new ArrayList<>();
            poliz.addAll(operand1.toPoliz());
            poliz.addAll(operand2.toPoliz());
            poliz.addAll(operator.toPoliz());
            return poliz;
        }
        return operand1.toPoliz();
    }

    @Override
    public VariableType type() {
        if (operator == null) {
            return operand1.type();
        }
        VariableType type1 = operand1.type();
        VariableType type2 = operand2.type();
        if (operator.leftOperandTypes().contains(type1) && operator.rightOperandTypes().contains(type2)) {
            return operator.resultType(type1, type2);
        } else {
            throw new TypeError(type1 + ", " + type2 + " is incorrect for operator " + operator);
        }
    }
}
