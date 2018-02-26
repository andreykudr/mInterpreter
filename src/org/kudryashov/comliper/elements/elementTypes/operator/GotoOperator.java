package org.kudryashov.comliper.elements.elementTypes.operator;

import org.kudryashov.comliper.elements.elementTypes.ElementName;

import java.util.Collections;
import java.util.List;

import static org.kudryashov.comliper.elements.elementTypes.util.poliz.AdditionalPolizElements.GOTO;

public class GotoOperator implements Operator {

    @Override
    public Dimension dimension() {
        return Dimension.MULTIPLE;
    }

    @Override
    public List<ElementName> toPoliz() {
        return Collections.singletonList(GOTO);
    }
}
