package org.kudryashov.comliper.syntax.rule.operator;

import org.kudryashov.comliper.elements.elementTypes.ElementName;
import org.kudryashov.comliper.elements.elementTypes.Identifier;
import org.kudryashov.comliper.elements.elementTypes.word.ReadOperator;
import org.kudryashov.comliper.syntax.Context;
import org.kudryashov.comliper.syntax.rule.SyntaxRule;

import java.util.ArrayList;
import java.util.List;

import static org.kudryashov.comliper.elements.elementTypes.separator.Separator.Name.*;
import static org.kudryashov.comliper.elements.elementTypes.word.enumeration.Word.READ;

public class ReadOperatorRule implements SyntaxRule<ReadOperator> {

    private List<Identifier> identifiers = new ArrayList<>();

    @Override
    public ReadOperator apply(Context lexemes) {
        if (check(lexemes.pop(), READ) && check(lexemes.pop(), LEFTBRACE)) {
            identifiers = checkIdentifiers(lexemes);
            if (!identifiers.isEmpty() && check(lexemes.pop(), RIGHTBRACE)) {
                return new ReadOperator();
            }
        }
        return null;
    }

    private List<Identifier> checkIdentifiers(Context lexemes) {
        for (;;) {
            Identifier identifier = getIdentifier(lexemes);
            if (identifier != null) {
                identifiers.add(identifier);
            } else if (notCommaNext(lexemes)) {
                return identifiers;
            } else {
                lexemes.pop();
            }
        }
    }

    private boolean notCommaNext(Context lexemes) {
        return !check(lexemes.peek(), COMMA);
    }

    private Identifier getIdentifier(Context lexemes) {
        ElementName name = lexemes.peek();
        if (name instanceof Identifier.Name) {
            lexemes.pop();
            return new Identifier((Identifier.Name) name);
        }
        return null;
    }
}
