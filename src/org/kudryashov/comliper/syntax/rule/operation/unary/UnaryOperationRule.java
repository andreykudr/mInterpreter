package org.kudryashov.comliper.syntax.rule.operation.unary;

import org.kudryashov.comliper.elements.elementTypes.ElementName;
import org.kudryashov.comliper.elements.elementTypes.operator.NotOperator;
import org.kudryashov.comliper.elements.elementTypes.operator.UnaryOperator;
import org.kudryashov.comliper.elements.elementTypes.word.enumeration.Word;
import org.kudryashov.comliper.syntax.Context;
import org.kudryashov.comliper.syntax.rule.SyntaxRule;

public class UnaryOperationRule implements SyntaxRule<UnaryOperator> {

    @Override
    public UnaryOperator apply(Context lexemes) {
        ElementName element = lexemes.pop();
        return element.equals(Word.NOT) ? new NotOperator() : null;
    }
}
