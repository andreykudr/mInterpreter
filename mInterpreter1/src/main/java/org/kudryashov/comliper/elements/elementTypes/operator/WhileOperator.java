package org.kudryashov.comliper.elements.elementTypes.operator;

import org.kudryashov.comliper.elements.elementTypes.ElementName;
import org.kudryashov.comliper.elements.elementTypes.util.Expression;
import org.kudryashov.comliper.elements.elementTypes.util.poliz.PolizElementNumber;
import org.kudryashov.comliper.elements.elementTypes.word.ReservedWord;
import org.kudryashov.comliper.elements.elementTypes.word.enumeration.Word;

import java.util.ArrayList;
import java.util.List;

import static org.kudryashov.comliper.elements.elementTypes.util.poliz.AdditionalPolizElements.GOTO;
import static org.kudryashov.comliper.elements.elementTypes.util.poliz.AdditionalPolizElements.REVERT_IF;

public class WhileOperator extends ReservedWord implements TripleOperator {

    private final Expression expression;
    private final Operator operator;

    public WhileOperator(Expression expression, Operator operator) {

        this.expression = expression;
        this.operator = operator;
    }

    @Override
    public Word getWord() {
        return Word.WHILE;
    }

    @Override
    public List<ElementName> toPoliz() {
        ArrayList<ElementName> poliz = new ArrayList<>();
        poliz.addAll(expression.toPoliz());
        PolizElementNumber endOfWhile = new PolizElementNumber();
        poliz.add(endOfWhile);
        poliz.add(REVERT_IF);
        poliz.addAll(operator.toPoliz());
        poliz.add(new PolizElementNumber(0));
        poliz.add(GOTO);
        endOfWhile.number = poliz.size();
        return poliz;
    }
}
