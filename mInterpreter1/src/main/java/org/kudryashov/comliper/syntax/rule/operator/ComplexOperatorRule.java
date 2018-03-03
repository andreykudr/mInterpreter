package org.kudryashov.comliper.syntax.rule.operator;

import org.kudryashov.comliper.elements.elementTypes.util.ComplexOperator;
import org.kudryashov.comliper.syntax.Context;
import org.kudryashov.comliper.syntax.rule.SyntaxRule;

import static org.kudryashov.comliper.elements.elementTypes.word.enumeration.Word.BEGIN;
import static org.kudryashov.comliper.elements.elementTypes.word.enumeration.Word.END;

public class ComplexOperatorRule implements SyntaxRule<ComplexOperator> {

    @Override
    public ComplexOperator apply(Context lexemes) {
        ComplexOperator operator = null;
        if (check(lexemes.pop(), BEGIN)) {
            operator = new OperatorsRule().apply(lexemes);
        }
        if (operator != null &&check(lexemes.pop(), END)) {
            return operator;
        }
        return null;
    }
}
