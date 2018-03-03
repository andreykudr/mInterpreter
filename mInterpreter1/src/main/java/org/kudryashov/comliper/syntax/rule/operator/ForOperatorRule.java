package org.kudryashov.comliper.syntax.rule.operator;

import org.kudryashov.comliper.Instances;
import org.kudryashov.comliper.elements.elementTypes.operator.AsOperator;
import org.kudryashov.comliper.elements.elementTypes.operator.ForOperator;
import org.kudryashov.comliper.elements.elementTypes.operator.Operator;
import org.kudryashov.comliper.elements.elementTypes.repositories.Repository;
import org.kudryashov.comliper.elements.elementTypes.util.Expression;
import org.kudryashov.comliper.elements.elementTypes.word.ReservedWord;
import org.kudryashov.comliper.syntax.Context;
import org.kudryashov.comliper.syntax.rule.SyntaxRule;

import static org.kudryashov.comliper.elements.elementTypes.word.enumeration.Word.*;

public class ForOperatorRule implements SyntaxRule<ForOperator> {

    private Repository<ReservedWord> words = Instances.get(Instances.WORDS);
    private AsOperator asOperator;
    private Expression expression;
    private Operator operator;

    @Override
    public ForOperator apply(Context lexemes) {
        if (check(lexemes.pop(), FOR) && checkIf(lexemes)) {
                return new ForOperator(asOperator, expression, operator);
        }
        return null;
    }

    private boolean checkIf(Context lexemes) {
        asOperator = new AssignOperatorRule().apply(lexemes);
        if (asOperator != null && check(lexemes.pop(), TO)) {
            expression = new ExpressionRule().apply(lexemes);
            if (expression != null && check(lexemes.pop(), DO)) {
                operator = new OperatorRule().apply(lexemes);
                if (operator != null) {
                    return true;
                }
            }
        }
        return false;
    }
}
