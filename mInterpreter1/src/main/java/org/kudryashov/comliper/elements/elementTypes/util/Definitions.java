package org.kudryashov.comliper.elements.elementTypes.util;

import org.kudryashov.comliper.elements.elementTypes.ElementName;
import org.kudryashov.comliper.elements.elementTypes.Identifier;
import org.kudryashov.comliper.elements.elementTypes.ProgramElement;

import java.util.List;

import static java.util.Collections.emptyList;

public class Definitions implements ProgramElement {

    private List<Identifier> identifiers;

    public Definitions(List<Identifier> identifiers) {
        this.identifiers = identifiers;
    }

    @Override
    public List<ElementName> toPoliz() {
        return emptyList();
    }

    public boolean isEmpty() {
        return identifiers.isEmpty();
    }

    public boolean allDefined() {
        for (Identifier identifier : identifiers) {
            if (!identifier.defined()) {
                return false;
            }
        }
        return true;
    }
}
