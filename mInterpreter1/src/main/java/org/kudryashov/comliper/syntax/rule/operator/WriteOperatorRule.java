package org.kudryashov.comliper.syntax.rule.operator;

import org.kudryashov.comliper.elements.elementTypes.util.Expression;
import org.kudryashov.comliper.elements.elementTypes.word.WriteOperator;
import org.kudryashov.comliper.syntax.Context;
import org.kudryashov.comliper.syntax.rule.SyntaxRule;

import java.util.ArrayList;
import java.util.List;

import static org.kudryashov.comliper.elements.elementTypes.separator.Separator.Name.*;
import static org.kudryashov.comliper.elements.elementTypes.word.enumeration.Word.WRITE;

public class WriteOperatorRule implements SyntaxRule<WriteOperator> {

    private List<Expression> expressions = new ArrayList<>();

    @Override
    public WriteOperator apply(Context lexemes) {
        if (check(lexemes.pop(), WRITE)
                && check(lexemes.pop(), LEFTBRACE)
                && checkExpressions(lexemes)
                && check(lexemes.pop(), RIGHTBRACE)) {
            return new WriteOperator(expressions);
        }
        return null;
    }

    private boolean checkExpressions(Context lexemes) {
        for (;;) {
            Expression expression = new ExpressionRule().apply(lexemes);
            if (expression != null) {
                expressions.add(expression);
                if (notCommaNext(lexemes)) {
                    return true;
                } else {
                    lexemes.pop();
                }
            } else {
                return false;
            }
        }
    }

    private boolean notCommaNext(Context lexemes) {
        return !check(lexemes.peek(), COMMA);
    }
}
