package org.kudryashov.comliper.elements.elementTypes.word.type;

import org.kudryashov.comliper.elements.elementTypes.ElementName;
import org.kudryashov.comliper.elements.elementTypes.word.ReservedWord;

import java.util.Collections;
import java.util.List;

public abstract class VariableType extends ReservedWord {

    @Override
    public List<ElementName> toPoliz() {
        return Collections.emptyList();
    }
}
