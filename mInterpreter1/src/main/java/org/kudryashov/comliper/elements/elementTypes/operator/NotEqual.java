package org.kudryashov.comliper.elements.elementTypes.operator;

import org.kudryashov.comliper.Instances;
import org.kudryashov.comliper.elements.elementTypes.ProgramElement;
import org.kudryashov.comliper.elements.elementTypes.separator.Separator;
import org.kudryashov.comliper.elements.elementTypes.word.type.VariableType;
import org.kudryashov.comliper.elements.elementTypes.word.type.VariableTypes;

import java.util.HashSet;
import java.util.Set;

import static org.kudryashov.comliper.Instances.VARIABLE_TYPES;
import static org.kudryashov.comliper.elements.elementTypes.word.type.VariableTypes.BOOL;

public class NotEqual extends Separator implements BinaryOperator {

    private static final VariableTypes TYPES = Instances.get(VARIABLE_TYPES);
    private static final HashSet<VariableType> ALL_TYPES = new HashSet<>(TYPES.getAll());

    @Override
    public Set<VariableType> leftOperandTypes() {
        return ALL_TYPES;
    }

    @Override
    public Set<VariableType> rightOperandTypes() {
        return ALL_TYPES;
    }

    @Override
    public VariableType resultType(VariableType left, VariableType right) {
        return BOOL;
    }

    @Override
    public Name getName() {
        return Name.NOT_EQUALS;
    }
}
