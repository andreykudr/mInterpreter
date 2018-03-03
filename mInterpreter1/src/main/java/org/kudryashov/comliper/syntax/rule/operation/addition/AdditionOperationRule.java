package org.kudryashov.comliper.syntax.rule.operation.addition;

import org.kudryashov.comliper.elements.elementTypes.ElementName;
import org.kudryashov.comliper.elements.elementTypes.operator.BinaryOperator;
import org.kudryashov.comliper.elements.elementTypes.operator.LogicOr;
import org.kudryashov.comliper.elements.elementTypes.operator.Minus;
import org.kudryashov.comliper.elements.elementTypes.operator.Plus;
import org.kudryashov.comliper.elements.elementTypes.separator.Separator;
import org.kudryashov.comliper.elements.elementTypes.separator.Separator.Name;
import org.kudryashov.comliper.lexical.LexicalElement;
import org.kudryashov.comliper.syntax.Context;
import org.kudryashov.comliper.syntax.rule.SyntaxRule;

import java.util.Map;

import static org.kudryashov.comliper.elements.elementTypes.separator.Separator.Name.*;

public class AdditionOperationRule implements SyntaxRule<BinaryOperator> {

    @Override
    public BinaryOperator apply(Context lexemes) {
        ElementName el = lexemes.pop();
        if (el.equals(PLUS)) {
            return new Plus();
        }
        if (el.equals(MINUS)) {
            return new Minus();
        }
        if (el.equals(LOGIC_OR)) {
            return new LogicOr();
        }
        return null;
    }
}
