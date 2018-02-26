package org.kudryashov.comliper.syntax.rule;

import org.kudryashov.comliper.elements.elementTypes.operator.BinaryOperator;
import org.kudryashov.comliper.elements.elementTypes.operator.Operator;
import org.kudryashov.comliper.elements.elementTypes.util.Composed;
import org.kudryashov.comliper.elements.elementTypes.util.Multiplier;
import org.kudryashov.comliper.syntax.Context;
import org.kudryashov.comliper.syntax.rule.operation.multiply.MultiplyGroupOperationRule;

public class ComposedRule implements SyntaxRule<Composed> {

    @Override
    public Composed apply(Context lexemes) {
        Multiplier multiplier1 = new MultiplierRule().apply(lexemes);
        if (multiplier1 != null) {
            BinaryOperator operator = new MultiplyGroupOperationRule().applyWithReset(lexemes);
            if (operator != null) {
                Multiplier multiplier2 = new MultiplierRule().apply(lexemes);
                if (multiplier2 != null) {
                    return new Composed(
                            multiplier1,
                            operator,
                            multiplier2);
                }
            } else {
                return new Composed(multiplier1);
            }
        }
        return null;
    }
}
