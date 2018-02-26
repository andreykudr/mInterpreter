package org.kudryashov.comliper.elements.elementTypes.operator;

import org.kudryashov.comliper.Instances;
import org.kudryashov.comliper.elements.elementTypes.ElementName;
import org.kudryashov.comliper.elements.elementTypes.Identifier;
import org.kudryashov.comliper.elements.elementTypes.util.Expression;
import org.kudryashov.comliper.elements.elementTypes.util.TypedProgramElement;
import org.kudryashov.comliper.elements.elementTypes.word.ReservedWord;
import org.kudryashov.comliper.elements.elementTypes.word.enumeration.Word;
import org.kudryashov.comliper.elements.elementTypes.word.type.VariableType;
import org.kudryashov.comliper.elements.elementTypes.word.type.VariableTypes;
import org.kudryashov.comliper.syntax.exception.SyntaxError;

import java.util.*;

import static org.kudryashov.comliper.Instances.VARIABLE_TYPES;

public class AsOperator extends ReservedWord implements BinaryOperator<Identifier, Expression> {

    private static final VariableTypes TYPES = Instances.get(VARIABLE_TYPES);
    private static final HashSet<VariableType> ALL_TYPES = new HashSet<>(TYPES.getAll());
    private static final Word word = Word.AS;
    private Identifier identifier;
    private Expression expression;


    public AsOperator(Identifier identifier, Expression expression) {
        if (identifier == null || expression == null) {
            throw new SyntaxError();
        }
        if (!identifier.type().equals(expression.type())) {
            throw new TypedProgramElement.TypeError(identifier.type() + ", " + expression.type());
        }
        this.identifier = identifier;
        this.expression = expression;
    }

    public Word getWord() {
        return word;
    }

    @Override
    public Set<VariableType> leftOperandTypes() {
        return ALL_TYPES;
    }

    @Override
    public Set<VariableType> rightOperandTypes() {
        return ALL_TYPES;
    }

    @Override
    public VariableType resultType(VariableType left, VariableType right) {
        return right;
    }

    @Override
    public List<ElementName> toPoliz() {
        ArrayList<ElementName> poliz = new ArrayList<>();
        poliz.addAll(identifier.toPoliz());
        poliz.addAll(expression.toPoliz());
        poliz.add(word);
        return poliz;
    }
}
