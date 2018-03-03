package org.kudryashov.comliper.syntax.rule.operation.relation;

import org.kudryashov.comliper.elements.elementTypes.ElementName;
import org.kudryashov.comliper.elements.elementTypes.operator.*;
import org.kudryashov.comliper.lexical.LexicalElement;
import org.kudryashov.comliper.syntax.Context;
import org.kudryashov.comliper.syntax.rule.SyntaxRule;

import static org.kudryashov.comliper.elements.elementTypes.separator.Separator.Name.*;

public class RelationOperationRule implements SyntaxRule<BinaryOperator> {
    @Override
    public BinaryOperator apply(Context lexemes) {
        ElementName e = lexemes.pop();
        if (e.equals(MORE)) {
            return new More();
        }
        if (e.equals(LESS)) {
            return new Less();
        }
        if (e.equals(NOT_EQUALS)) {
            return new NotEqual();
        }
        if (e.equals(EQUAL)) {
            return new Equal();
        }
        if (e.equals(LESS_OR_EQUALS)) {
            return new LessOrEqual();
        }
        if (e.equals(MORE_OR_EQUALS)) {
            return new MoreOrEqual();
        }
        return null;
    }
}
