package org.kudryashov.comliper.syntax.rule.operator;

import org.kudryashov.comliper.elements.elementTypes.operator.Operator;
import org.kudryashov.comliper.elements.elementTypes.util.ComplexOperator;
import org.kudryashov.comliper.syntax.Context;
import org.kudryashov.comliper.syntax.rule.SyntaxRule;

import java.util.ArrayList;
import java.util.List;

import static org.kudryashov.comliper.elements.elementTypes.separator.Separator.Name.SEMICOLON;

public class OperatorsRule implements SyntaxRule<ComplexOperator> {

    private List<Operator> operators = new ArrayList<>();

    @Override
    public ComplexOperator apply(Context lexemes) {
        for (; ; ) {
            Operator operator = new OperatorRule().apply(lexemes);
            if (operator == null) {
                return new ComplexOperator(operators);
            } else {
                operators.add(operator);
            }
            if (nextIsNotSemicolon(lexemes)) {
                return new ComplexOperator(operators);
            } else {
                lexemes.pop();
            }
        }
    }

    private boolean nextIsNotSemicolon(Context lexemes) {
        return !lexemes.peek().equals(SEMICOLON);
    }
}
