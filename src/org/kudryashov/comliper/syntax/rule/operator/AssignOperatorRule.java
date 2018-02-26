package org.kudryashov.comliper.syntax.rule.operator;

import org.kudryashov.comliper.Instances;
import org.kudryashov.comliper.elements.elementTypes.ElementName;
import org.kudryashov.comliper.elements.elementTypes.Identifier;
import org.kudryashov.comliper.elements.elementTypes.operator.AsOperator;
import org.kudryashov.comliper.elements.elementTypes.repositories.Repository;
import org.kudryashov.comliper.elements.elementTypes.util.Expression;
import org.kudryashov.comliper.syntax.Context;
import org.kudryashov.comliper.syntax.rule.SyntaxRule;

import static org.kudryashov.comliper.elements.elementTypes.word.enumeration.Word.AS;

public class AssignOperatorRule implements SyntaxRule<AsOperator> {

    private Repository<Identifier> identifiers = Instances.get(Instances.IDENTIFIERS);
    private Identifier identifier;
    private Expression expression;

    @Override
    public AsOperator apply(Context lexemes) {
        fillIdentifier(lexemes);
        if (identifier != null && check(lexemes.pop(), AS)){
            expression = new ExpressionRule().apply(lexemes);
            if (expression != null) {
                return new AsOperator(identifier, expression);
            }
        }
        return null;
    }

    private void fillIdentifier(Context lexemes) {
        ElementName element = lexemes.pop();
        if (element instanceof Identifier.Name) {
            identifier = identifiers.get(element);
        }
    }
}
