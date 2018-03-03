package org.kudryashov.comliper.syntax.rule.operator;

import org.kudryashov.comliper.elements.elementTypes.operator.Operator;
import org.kudryashov.comliper.syntax.Context;
import org.kudryashov.comliper.syntax.rule.SyntaxRule;

public class OperatorRule implements SyntaxRule<Operator> {

    private Operator operator;

    @Override
    public Operator apply(Context lexemes) {
        fillOperator(lexemes, new AssignOperatorRule());
        if (operator != null) return operator;
        fillOperator(lexemes, new IfOperatorRule());
        if (operator != null) return operator;
        fillOperator(lexemes, new ForOperatorRule());
        if (operator != null) return operator;
        fillOperator(lexemes, new WhileOperatorRule());
        if (operator != null) return operator;
        fillOperator(lexemes, new ReadOperatorRule());
        if (operator != null) return operator;
        fillOperator(lexemes, new WriteOperatorRule());
        if (operator != null) return operator;
        fillOperator(lexemes, new ComplexOperatorRule());
        if (operator != null) return operator;
        return null;
    }

    private void fillOperator(Context lexemes, SyntaxRule<? extends Operator> rule) {
        Operator operator = rule.applyWithReset(lexemes);
        if (operator != null) {
            this.operator = operator;
        } else {
            this.operator = null;
        }
    }

}
