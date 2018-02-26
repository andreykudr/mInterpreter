package org.kudryashov.comliper.elements.elementTypes.operator;

import org.kudryashov.comliper.Instances;
import org.kudryashov.comliper.elements.elementTypes.ElementName;
import org.kudryashov.comliper.elements.elementTypes.ProgramElement;
import org.kudryashov.comliper.elements.elementTypes.repositories.Repository;
import org.kudryashov.comliper.elements.elementTypes.word.ReservedWord;
import org.kudryashov.comliper.elements.elementTypes.word.enumeration.Word;
import org.kudryashov.comliper.elements.elementTypes.word.type.VariableType;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.kudryashov.comliper.Instances.VARIABLE_TYPES;
import static org.kudryashov.comliper.elements.elementTypes.word.type.VariableTypes.BOOL;

public class NotOperator extends ReservedWord implements UnaryOperator {

    private Repository<VariableType> types = Instances.get(VARIABLE_TYPES);

    @Override
    public Word getWord() {
        return Word.NOT;
    }

    @Override
    public Collection<VariableType> operandTypes() {
        return Collections.singleton(BOOL);
    }

    @Override
    public VariableType resultType(VariableType type) {
        return types.get(Word.BOOL);
    }

    @Override
    public List<ElementName> toPoliz() {
        return singletonList(getWord());
    }
}
