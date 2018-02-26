package org.kudryashov.comliper.elements.elementTypes.word;

import org.kudryashov.comliper.elements.elementTypes.ElementName;
import org.kudryashov.comliper.elements.elementTypes.word.enumeration.Word;

import java.util.Collections;
import java.util.List;

import static org.kudryashov.comliper.elements.elementTypes.word.enumeration.Word.PROGRAM;

public class ProgramWord extends ReservedWord {

    @Override
    public Word getWord() {
        return PROGRAM;
    }

    @Override
    public List<ElementName> toPoliz() {
        return Collections.emptyList();
    }
}
