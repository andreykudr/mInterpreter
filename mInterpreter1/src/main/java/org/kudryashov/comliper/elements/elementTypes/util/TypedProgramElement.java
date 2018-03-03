package org.kudryashov.comliper.elements.elementTypes.util;

import org.kudryashov.comliper.elements.elementTypes.ProgramElement;
import org.kudryashov.comliper.elements.elementTypes.word.type.VariableType;
import org.kudryashov.comliper.semantyc.exception.SemanticExpressionError;

public interface TypedProgramElement extends ProgramElement {

    VariableType type();

    class TypeError extends SemanticExpressionError {
        public TypeError(String s) {
            super(s);
        }
    }
}
