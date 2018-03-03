package org.kudryashov.comliper.elements.elementTypes.word;

import org.kudryashov.comliper.elements.elementTypes.word.enumeration.Word;
import org.kudryashov.comliper.lexical.LexicalElement;

public abstract class ReservedWord extends LexicalElement {

    public abstract Word getWord();
}
