package org.kudryashov.comliper.elements.elementTypes.word;

import org.kudryashov.comliper.elements.elementTypes.ElementName;
import org.kudryashov.comliper.elements.elementTypes.word.enumeration.Word;

import java.util.Collections;
import java.util.List;

public class ThenWord extends ReservedWord {
    @Override
    public Word getWord() {
        return Word.THEN;
    }

    @Override
    public List<ElementName> toPoliz() {
        return Collections.emptyList();
    }
}
