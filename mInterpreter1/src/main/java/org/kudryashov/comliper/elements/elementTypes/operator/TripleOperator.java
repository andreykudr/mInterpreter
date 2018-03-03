package org.kudryashov.comliper.elements.elementTypes.operator;

import org.kudryashov.comliper.elements.elementTypes.ProgramElement;

import static org.kudryashov.comliper.elements.elementTypes.operator.Operator.Dimension.TRIPLE;

public interface TripleOperator <First extends ProgramElement, Second extends ProgramElement, Third extends ProgramElement> extends Operator {

    @Override
    default Dimension dimension() {
        return TRIPLE;
    }



}
