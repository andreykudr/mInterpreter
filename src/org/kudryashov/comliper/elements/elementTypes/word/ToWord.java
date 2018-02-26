package org.kudryashov.comliper.elements.elementTypes.word;

import org.kudryashov.comliper.elements.elementTypes.ElementName;
import org.kudryashov.comliper.elements.elementTypes.word.enumeration.Word;

import java.util.List;

public class ToWord extends ReservedWord {
    @Override
    public Word getWord() {
        return Word.TO;
    }

    @Override
    public List<ElementName> toPoliz() {
        return null;
    }
}
