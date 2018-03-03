package org.kudryashov.comliper.syntax.rule;

import org.kudryashov.comliper.Instances;
import org.kudryashov.comliper.elements.elementTypes.ElementName;
import org.kudryashov.comliper.elements.elementTypes.Identifier;
import org.kudryashov.comliper.elements.elementTypes.NumberElement;
import org.kudryashov.comliper.elements.elementTypes.ProgramElement;
import org.kudryashov.comliper.elements.elementTypes.operator.UnaryOperator;
import org.kudryashov.comliper.elements.elementTypes.repositories.Repository;
import org.kudryashov.comliper.elements.elementTypes.util.Expression;
import org.kudryashov.comliper.elements.elementTypes.util.Multiplier;
import org.kudryashov.comliper.elements.elementTypes.word.type.BoolType;
import org.kudryashov.comliper.syntax.Context;
import org.kudryashov.comliper.syntax.exception.SyntaxError;
import org.kudryashov.comliper.syntax.rule.operation.unary.UnaryOperationRule;
import org.kudryashov.comliper.syntax.rule.operator.ExpressionRule;

import static org.kudryashov.comliper.Instances.IDENTIFIERS;
import static org.kudryashov.comliper.elements.elementTypes.word.enumeration.Word.FALSE;
import static org.kudryashov.comliper.elements.elementTypes.word.enumeration.Word.TRUE;

public class MultiplierRule implements SyntaxRule {

    private final Repository<Identifier> identifiers = Instances.get(IDENTIFIERS);
    private UnaryOperator unaryOperator;
    private Multiplier multiplier;

    @Override
    public Multiplier apply(Context lexemes) {
        if (checkUnaryOperator(lexemes)) {
            return new Multiplier(unaryOperator, this.multiplier);
        }
        ElementName name = lexemes.pop();
        Multiplier result = null;
        if (isIdentifier(name)) {
            result = new Multiplier(identifiers.get(name));
        } else if (isNumber(name)) {
            result = new Multiplier(new NumberElement((NumberElement.Value) name));
        } else if (isBoolean(name)) {
            result = new Multiplier(BoolType.get(name));
        } else {
            Expression expression = new ExpressionRule().applyWithReset(lexemes);
            if (expression != null) {
                result = new Multiplier(expression);
            }
        }
        return result;
    }

    private boolean checkUnaryOperator(Context lexemes) {
        unaryOperator = new UnaryOperationRule().applyWithReset(lexemes);
        if (unaryOperator != null) {
            multiplier = new MultiplierRule().apply(lexemes);
            if (multiplier == null) {
                throw new SyntaxError("expected multiplier");
            }
            return true;
        }
        return false;
    }

    private boolean isBoolean(ElementName element) {
        return element.equals(TRUE) || element.equals(FALSE);
    }

    private boolean isNumber(ElementName element) {
        return element instanceof NumberElement.Value;
    }

    private boolean isIdentifier(ElementName element) {
        return element instanceof Identifier.Name;
    }

}
