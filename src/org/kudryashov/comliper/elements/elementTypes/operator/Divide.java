package org.kudryashov.comliper.elements.elementTypes.operator;

import org.kudryashov.comliper.elements.elementTypes.ProgramElement;
import org.kudryashov.comliper.elements.elementTypes.separator.Separator;
import org.kudryashov.comliper.elements.elementTypes.word.type.VariableType;

import java.util.*;

import static java.util.Arrays.asList;
import static org.kudryashov.comliper.elements.elementTypes.separator.Separator.Name.DIVIDE;
import static org.kudryashov.comliper.elements.elementTypes.word.type.VariableTypes.FLOAT;
import static org.kudryashov.comliper.elements.elementTypes.word.type.VariableTypes.INT;

public class Divide extends Separator implements BinaryOperator {

    private static final HashSet<VariableType> AVAILABLE_TYPES = new HashSet<>(asList(FLOAT, INT));
    private Name name = DIVIDE;

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
        return left.equals(FLOAT) || right.equals(FLOAT) ? FLOAT : INT;
    }
    @Override
    public Name getName() {
        return DIVIDE;
    }
}
