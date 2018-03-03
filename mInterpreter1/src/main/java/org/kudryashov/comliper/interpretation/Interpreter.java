package org.kudryashov.comliper.interpretation;

import org.kudryashov.comliper.elements.elementTypes.ElementName;
import org.kudryashov.comliper.elements.elementTypes.Identifier;
import org.kudryashov.comliper.elements.elementTypes.repositories.Repository;

import java.util.List;

public interface Interpreter {

    void interpret(List<ElementName> poliz, Repository<Identifier> identifiers);
}
