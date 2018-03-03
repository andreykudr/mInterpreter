package org.kudryashov.comliper.lexical.context;

import org.kudryashov.comliper.elements.elementTypes.ElementName;
import org.kudryashov.comliper.lexical.LexicalElement;

import java.util.List;

public interface LexicalContext {

    char currentCharacter();

    boolean hasNextCharacter();

    void readNextCharacter();

    void putOutputElement(ElementName element);

    List<ElementName> getOutput();

    void clearBuffer();

    StringBuffer getBuffer();
}
