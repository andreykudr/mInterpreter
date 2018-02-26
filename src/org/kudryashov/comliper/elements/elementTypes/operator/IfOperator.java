package org.kudryashov.comliper.elements.elementTypes.operator;

import org.kudryashov.comliper.elements.elementTypes.ElementName;
import org.kudryashov.comliper.elements.elementTypes.ProgramElement;
import org.kudryashov.comliper.elements.elementTypes.util.Expression;
import org.kudryashov.comliper.elements.elementTypes.util.poliz.AdditionalPolizElements;
import org.kudryashov.comliper.elements.elementTypes.util.poliz.PolizElementNumber;
import org.kudryashov.comliper.elements.elementTypes.word.ReservedWord;
import org.kudryashov.comliper.elements.elementTypes.word.enumeration.Word;
import org.kudryashov.comliper.elements.elementTypes.word.type.VariableType;

import java.util.*;

import static org.kudryashov.comliper.elements.elementTypes.util.poliz.AdditionalPolizElements.GOTO;
import static org.kudryashov.comliper.elements.elementTypes.util.poliz.AdditionalPolizElements.REVERT_IF;
import static org.kudryashov.comliper.elements.elementTypes.word.type.VariableTypes.BOOL;

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
        PolizElementNumber elseBranchIndex = new PolizElementNumber();
        poliz.add(elseBranchIndex);
        poliz.add(REVERT_IF);
        poliz.addAll(operator.toPoliz());
        PolizElementNumber endIfIndex = new PolizElementNumber();
        poliz.add(endIfIndex);
        poliz.add(GOTO);
        elseBranchIndex.number = poliz.size();
        if (elseOperator != null) {
            poliz.addAll(elseOperator.toPoliz());
        }
        endIfIndex.number = poliz.size();
        return poliz;
    }
}
