package org.kudryashov.comliper.elements.elementTypes.operator;

import org.kudryashov.comliper.elements.elementTypes.word.type.VariableType;

import java.util.Collection;

public interface UnaryOperator extends Operator {

    Collection<VariableType> operandTypes();

    VariableType resultType(VariableType type);

    @Override
    default Dimension dimension() {
        return Dimension.UNARY;
    }
}
