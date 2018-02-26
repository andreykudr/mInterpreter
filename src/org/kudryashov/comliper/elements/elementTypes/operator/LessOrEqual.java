package org.kudryashov.comliper.elements.elementTypes.operator;

import org.kudryashov.comliper.elements.elementTypes.ProgramElement;
import org.kudryashov.comliper.elements.elementTypes.separator.Separator;
import org.kudryashov.comliper.elements.elementTypes.word.type.VariableType;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static java.util.Arrays.asList;
import static org.kudryashov.comliper.elements.elementTypes.separator.Separator.Name.LESS_OR_EQUALS;
import static org.kudryashov.comliper.elements.elementTypes.word.type.VariableTypes.*;

public class LessOrEqual extends Separator implements BinaryOperator {

    private static final HashSet<VariableType> AVAILABLE_TYPES = new HashSet<>(asList(INT, FLOAT));
    private final Name lessOrEquals = LESS_OR_EQUALS;

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
        LessOrEqual that = (LessOrEqual) o;
        return lessOrEquals == that.lessOrEquals;
    }

    @Override
    public int hashCode() {

        return Objects.hash(lessOrEquals);
    }

    @Override
    public Name getName() {
        return LESS_OR_EQUALS;
    }
}
