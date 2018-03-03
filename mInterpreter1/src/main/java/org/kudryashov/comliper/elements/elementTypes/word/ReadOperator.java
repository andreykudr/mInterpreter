package org.kudryashov.comliper.elements.elementTypes.word;

import org.kudryashov.comliper.elements.elementTypes.ElementName;
import org.kudryashov.comliper.elements.elementTypes.operator.MultipleOperator;
import org.kudryashov.comliper.elements.elementTypes.word.enumeration.Word;

import java.util.List;

public class ReadOperator extends ReservedWord implements MultipleOperator {
    @Override
    public Word getWord() {
        return Word.READ;
    }

    @Override
    public List<ElementName> toPoliz() {
        return null;
    }
}
