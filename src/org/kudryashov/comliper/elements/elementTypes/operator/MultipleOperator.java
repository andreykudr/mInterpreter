package org.kudryashov.comliper.elements.elementTypes.operator;

public interface MultipleOperator extends Operator {

    @Override
    default Dimension dimension() {
        return Dimension.MULTIPLE;
    }
}
