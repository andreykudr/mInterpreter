package org.kudryashov.comliper.elements.elementTypes.operator;

import org.kudryashov.comliper.elements.elementTypes.separator.Separator;
import org.kudryashov.comliper.elements.elementTypes.word.type.VariableType;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static java.util.Arrays.asList;
import static org.kudryashov.comliper.elements.elementTypes.separator.Separator.Name.LESS;
import static org.kudryashov.comliper.elements.elementTypes.word.type.VariableTypes.*;

public class Less extends Separator implements BinaryOperator {

    private static final HashSet<VariableType> AVAILABLE_TYPES = new HashSet<>(asList(INT, FLOAT));
    private final Name name = LESS;

    @Override
    public Set<VariableType> leftOperandTypes() {
        return AVAILABLE_TYPES;
    }

    @Override
    public Set<VariableType> rightOperandTypes() {
        return AVAILABLE_TYPES;
    }

    @Override
    public VariableType resultType(VariableType left, VariableType right) {
        return BOOL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Less less = (Less) o;
        return name == less.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public Name getName() {
        return LESS;
    }
}
