package org.kudryashov.comliper.elements.elementTypes.word;

import org.kudryashov.comliper.elements.elementTypes.ElementName;
import org.kudryashov.comliper.elements.elementTypes.operator.MultipleOperator;
import org.kudryashov.comliper.elements.elementTypes.util.Expression;
import org.kudryashov.comliper.elements.elementTypes.word.enumeration.Word;

import java.util.ArrayList;
import java.util.List;

public class WriteOperator extends ReservedWord implements MultipleOperator {
    private List<Expression> expressions;

    public WriteOperator(List<Expression> expressions) {

        this.expressions = expressions;
    }

    @Override
    public Word getWord() {
        return Word.WRITE;
    }

    @Override
    public List<ElementName> toPoliz() {
        ArrayList<ElementName> poliz = new ArrayList<>();
        for (Expression expression : expressions) {
            poliz.addAll(expression.toPoliz());
        }
        poliz.add(Word.WRITE);
        return poliz;
    }
}
