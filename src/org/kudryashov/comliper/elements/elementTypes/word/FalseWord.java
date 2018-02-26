package org.kudryashov.comliper.elements.elementTypes.word;

import org.kudryashov.comliper.elements.elementTypes.ElementName;
import org.kudryashov.comliper.elements.elementTypes.word.enumeration.Word;

import java.util.List;

public class FalseWord extends ReservedWord {
    @Override
    public Word getWord() {
        return Word.FALSE;
    }

    @Override
    public List<ElementName> toPoliz() {
        return null;
    }
}
