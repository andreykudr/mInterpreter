package org.kudryashov.comliper.elements.elementTypes.operator;

import org.kudryashov.comliper.elements.elementTypes.ElementName;
import org.kudryashov.comliper.elements.elementTypes.util.Expression;
import org.kudryashov.comliper.elements.elementTypes.util.poliz.GotoLabel;
import org.kudryashov.comliper.elements.elementTypes.util.poliz.PolizPointer;
import org.kudryashov.comliper.elements.elementTypes.word.ReservedWord;
import org.kudryashov.comliper.elements.elementTypes.word.enumeration.Word;
import org.kudryashov.comliper.elements.elementTypes.word.type.VariableType;

import java.util.*;

import static org.kudryashov.comliper.elements.elementTypes.util.poliz.AdditionalPolizElements.GOTO;
import static org.kudryashov.comliper.elements.elementTypes.util.poliz.AdditionalPolizElements.REVERT_IF;

public class IfOperator extends ReservedWord implements BinaryOperator<Expression, Operator> {

    private static final Word word = Word.IF;

    private final Expression expression;
    private final Operator operator;
    private final Operator elseOperator;

    public IfOperator(Expression expression, Operator operator, Operator elseOperator) {

        this.expression = expression;
        this.operator = operator;
        this.elseOperator = elseOperator;
    }

    @Override
    public Word getWord() {
        return word;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IfOperator ifWord = (IfOperator) o;
        return word == ifWord.word;
    }

    @Override
    public int hashCode() {

        return Objects.hash(word);
    }

    @Override
    public Set<VariableType> leftOperandTypes() {
        return new HashSet<>();
    }

    @Override
    public Set<VariableType> rightOperandTypes() {
        return null;
    }

    @Override
    public VariableType resultType(VariableType left, VariableType right) {
        return null;
    }

    @Override
    public List<ElementName> toPoliz() {
        ArrayList<ElementName> poliz = new ArrayList<>();
        poliz.addAll(expression.toPoliz());
        GotoLabel elseBranchLabel = new GotoLabel();
        PolizPointer elseBranchPointer = new PolizPointer(elseBranchLabel);
        poliz.add(elseBranchPointer);
        poliz.add(REVERT_IF);
        poliz.addAll(operator.toPoliz());
        GotoLabel endIfLabel = new GotoLabel();
        PolizPointer endIfPointer = new PolizPointer(endIfLabel);
        poliz.add(endIfPointer);
        poliz.add(GOTO);
        poliz.add(elseBranchLabel);
        if (elseOperator != null) {
            poliz.addAll(elseOperator.toPoliz());
        }
        poliz.add(new PolizPointer(endIfLabel));
        poliz.add(endIfLabel);
        return poliz;
    }
}
