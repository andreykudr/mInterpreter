package org.kudryashov.comliper.syntax.rule;

import org.kudryashov.comliper.elements.elementTypes.operator.BinaryOperator;
import org.kudryashov.comliper.elements.elementTypes.util.Composed;
import org.kudryashov.comliper.elements.elementTypes.util.Operand;
import org.kudryashov.comliper.syntax.Context;
import org.kudryashov.comliper.syntax.rule.operation.addition.AdditionOperationRule;

public class OperandRule implements SyntaxRule<Operand> {

    private Composed composed1;
    private BinaryOperator operator;
    private Composed composed2;

    @Override
    public Operand apply(Context lexemes) {
        fillComposed1(lexemes);
        fillOperator(lexemes);
        fillComposed2(lexemes);
        return new Operand(composed1, operator, composed2);
    }

    private void fillComposed1(Context lexemes) {
        composed1 = new ComposedRule().apply(lexemes);
    }

    private void fillOperator(Context lexemes) {
        operator = new AdditionOperationRule().applyWithReset(lexemes);
    }

    private void fillComposed2(Context lexemes) {
        if (operator != null) {
            composed2 = new ComposedRule().applyWithReset(lexemes);
        }
    }
}
