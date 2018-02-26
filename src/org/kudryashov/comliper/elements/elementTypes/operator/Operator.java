package org.kudryashov.comliper.elements.elementTypes.operator;

import org.kudryashov.comliper.elements.elementTypes.ProgramElement;

public interface Operator extends ProgramElement {

    Dimension dimension();

    enum Dimension {
        UNARY,
        BINARY,
        TRIPLE,
        MULTIPLE
    }

}
