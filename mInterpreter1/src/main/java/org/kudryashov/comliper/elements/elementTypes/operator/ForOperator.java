package org.kudryashov.comliper.elements.elementTypes.operator;

import org.kudryashov.comliper.elements.elementTypes.ElementName;
import org.kudryashov.comliper.elements.elementTypes.util.Expression;
import org.kudryashov.comliper.elements.elementTypes.util.poliz.GotoLabel;
import org.kudryashov.comliper.elements.elementTypes.util.poliz.PolizPointer;
import org.kudryashov.comliper.elements.elementTypes.word.ReservedWord;
import org.kudryashov.comliper.elements.elementTypes.word.enumeration.Word;

import java.util.ArrayList;
import java.util.List;

import static org.kudryashov.comliper.elements.elementTypes.util.poliz.AdditionalPolizElements.GOTO;
import static org.kudryashov.comliper.elements.elementTypes.util.poliz.AdditionalPolizElements.REVERT_IF;

public class ForOperator extends ReservedWord implements TripleOperator<AsOperator, Expression, Operator> {

    private final AsOperator asOperator;
    private final Expression expression;
    private final Operator operator;

    public ForOperator(AsOperator asOperator, Expression expression, Operator operator) {

        this.asOperator = asOperator;
        this.expression = expression;
        this.operator = operator;
    }

    @Override
    public Word getWord() {
        return Word.FOR;
    }

    @Override
    public List<ElementName> toPoliz() {
        ArrayList<ElementName> poliz = new ArrayList<>();
        poliz.addAll(asOperator.toPoliz());
        GotoLabel cycleStartLabel = new GotoLabel();
        poliz.add(cycleStartLabel);
        poliz.addAll(expression.toPoliz());
        GotoLabel endForLabel = new GotoLabel();
        PolizPointer endOfForPointer = new PolizPointer(endForLabel);
        poliz.add(endOfForPointer);
        poliz.add(REVERT_IF);
        poliz.addAll(operator.toPoliz());
        poliz.add(new PolizPointer(cycleStartLabel));
        poliz.add(GOTO);
        poliz.add(endForLabel);
        return poliz;
    }
}
