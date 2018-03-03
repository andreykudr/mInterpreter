package org.kudryashov.comliper.elements.elementTypes.operator;

import org.kudryashov.comliper.elements.elementTypes.ProgramElement;
import org.kudryashov.comliper.elements.elementTypes.word.type.VariableType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface BinaryOperator <L extends ProgramElement, R extends ProgramElement> extends Operator {

    Set<VariableType> leftOperandTypes();
    Set<VariableType> rightOperandTypes();
    VariableType resultType(VariableType left, VariableType right);

    @Override
    default Dimension dimension() {
        return Dimension.BINARY;
    }
}
