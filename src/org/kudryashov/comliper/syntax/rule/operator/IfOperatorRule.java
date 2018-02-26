package org.kudryashov.comliper.syntax.rule.operator;

import org.kudryashov.comliper.elements.elementTypes.operator.IfOperator;
import org.kudryashov.comliper.elements.elementTypes.operator.Operator;
import org.kudryashov.comliper.elements.elementTypes.util.Expression;
import org.kudryashov.comliper.syntax.Context;
import org.kudryashov.comliper.syntax.rule.SyntaxRule;

import static org.kudryashov.comliper.elements.elementTypes.word.enumeration.Word.*;

public class IfOperatorRule implements SyntaxRule<IfOperator> {

    private OperatorRule operatorRule;
    private Expression expression;
    private Operator operator;
    private Operator elseOperator;

    @Override
    public IfOperator apply(Context lexemes) {
        operatorRule = new OperatorRule();
        if (check(lexemes.pop(), IF)) {
            if (checkOperator(lexemes)) {
                return new IfOperator(expression, operator, elseOperator);
            }
        }
        return null;
    }

    private boolean checkOperator(Context lexemes) {
        expression = new ExpressionRule().apply(lexemes);
        if (expression != null && check(lexemes.pop(), THEN)) {
            operator = operatorRule.apply(lexemes);
        }
        elseOperator = checkElse(lexemes);
        return operator != null;
    }

    private Operator checkElse(Context lexemes) {
        if (check(lexemes.peek(), ELSE)) {
            lexemes.pop();
            return operatorRule.apply(lexemes);
        }
        return null;
    }
}
