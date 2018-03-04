package org.kudryashov.comliper.elements.elementTypes.word;

import org.kudryashov.comliper.elements.elementTypes.ElementName;
import org.kudryashov.comliper.elements.elementTypes.Identifier;
import org.kudryashov.comliper.elements.elementTypes.operator.MultipleOperator;
import org.kudryashov.comliper.elements.elementTypes.word.enumeration.Word;

import java.util.ArrayList;
import java.util.List;

public class ReadOperator extends ReservedWord implements MultipleOperator {

    private List<Identifier> identifiers;

    public ReadOperator(List<Identifier> identifiers) {

        this.identifiers = identifiers;
    }

    @Override
    public Word getWord() {
        return Word.READ;
    }

    @Override
    public List<ElementName> toPoliz() {
        ArrayList<ElementName> poliz = new ArrayList<>();
        for (Identifier identifier : identifiers) {
            poliz.addAll(identifier.toPoliz());
        }
        poliz.add(Word.READ);
        return poliz;
    }
}
