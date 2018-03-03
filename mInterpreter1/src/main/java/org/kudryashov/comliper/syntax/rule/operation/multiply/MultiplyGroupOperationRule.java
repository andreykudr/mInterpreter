package org.kudryashov.comliper.syntax.rule.operation.multiply;

import org.kudryashov.comliper.elements.elementTypes.ElementName;
import org.kudryashov.comliper.elements.elementTypes.operator.*;
import org.kudryashov.comliper.syntax.Context;
import org.kudryashov.comliper.syntax.rule.SyntaxRule;

import static org.kudryashov.comliper.elements.elementTypes.separator.Separator.Name.*;

public class MultiplyGroupOperationRule implements SyntaxRule<BinaryOperator> {
    @Override
    public BinaryOperator apply(Context lexemes) {
        ElementName el = lexemes.pop();
        if (el.equals(MULTIPLY)) {
            return new Multiply();
        } else if (el.equals(DIVIDE)) {
            return new Divide();
        } else if (el.equals(LOGIC_AND)) {
            return new LogicAnd();
        } else {
            return null;
        }
    }
}
