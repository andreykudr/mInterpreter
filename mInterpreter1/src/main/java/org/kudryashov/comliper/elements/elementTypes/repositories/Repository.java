package org.kudryashov.comliper.elements.elementTypes.repositories;

import org.kudryashov.comliper.elements.elementTypes.ElementName;
import org.kudryashov.comliper.lexical.LexicalElement;
import org.kudryashov.comliper.elements.elementTypes.word.enumeration.Word;

import java.util.Collection;

public interface Repository<T> {

    T get(ElementName name);

    Collection<T> getAll();
}
