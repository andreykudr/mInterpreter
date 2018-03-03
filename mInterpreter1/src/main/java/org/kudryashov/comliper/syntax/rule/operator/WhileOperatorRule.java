package org.kudryashov.comliper.syntax.rule.operator;

import org.kudryashov.comliper.Instances;
import org.kudryashov.comliper.elements.elementTypes.operator.Operator;
import org.kudryashov.comliper.elements.elementTypes.operator.WhileOperator;
import org.kudryashov.comliper.elements.elementTypes.repositories.Repository;
import org.kudryashov.comliper.elements.elementTypes.util.Expression;
import org.kudryashov.comliper.elements.elementTypes.word.ReservedWord;
import org.kudryashov.comliper.syntax.Context;
import org.kudryashov.comliper.syntax.rule.SyntaxRule;

import static org.kudryashov.comliper.elements.elementTypes.word.enumeration.Word.DO;
import static org.kudryashov.comliper.elements.elementTypes.word.enumeration.Word.WHILE;

public class WhileOperatorRule implements SyntaxRule<WhileOperator> {

    private Repository<ReservedWord> words = Instances.get(Instances.WORDS);
    private Expression expression;
    private Operator operator;

    @Override
    public WhileOperator apply(Context lexemes) {
        return check(lexemes.pop(), WHILE) && checkWhile(lexemes) ? new WhileOperator(expression, operator) : null;
    }

    private boolean checkWhile(Context lexemes) {
        expression = new ExpressionRule().apply(lexemes);
        if (expression != null && check(lexemes.pop(), DO)) {
            operator = new OperatorRule().apply(lexemes);
            if (operator != null) {
                return true;
            }
        }
        return false;
    }
}
