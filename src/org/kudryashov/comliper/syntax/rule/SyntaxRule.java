package org.kudryashov.comliper.syntax.rule;

import org.kudryashov.comliper.elements.elementTypes.ElementName;
import org.kudryashov.comliper.elements.elementTypes.ProgramElement;
import org.kudryashov.comliper.elements.elementTypes.operator.Operator;
import org.kudryashov.comliper.lexical.LexicalElement;
import org.kudryashov.comliper.syntax.Context;

public interface SyntaxRule<T extends ProgramElement> {

    T apply(Context lexemes);

    default T applyWithReset(Context lexemes) {
        lexemes.commit();
        T result = apply(lexemes);
        if (result != null) {
            lexemes.commit();
            return result;
        } else {
            lexemes.reset();
            return null;
        }
    }

    default boolean check(ElementName actual, ElementName... expected) {
        for (ElementName name : expected) {
            if (actual.equals(name)) {
                return true;
            }
        }
        return false;
    }
}
