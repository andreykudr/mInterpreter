package org.kudryashov.comliper.syntax.rule;

import org.kudryashov.comliper.elements.elementTypes.Identifier;
import org.kudryashov.comliper.elements.elementTypes.util.Definitions;
import org.kudryashov.comliper.semantyc.DefinitionSemanticRule;
import org.kudryashov.comliper.syntax.Context;

import java.util.ArrayList;
import java.util.List;

import static org.kudryashov.comliper.elements.elementTypes.separator.Separator.Name.COMMA;

public class DefinitionsSyntaxRule implements SyntaxRule<Definitions> {

    private List<Identifier> identifiers = new ArrayList<>();

    @Override
    public Definitions apply(Context lexemes) {
        for (;;) {
            Identifier identifier = new DefinitionSyntaxRule().apply(lexemes);
            if (identifier == null) {
                return new Definitions(identifiers);
            } else {
                identifiers.add(identifier);
                if (!check(lexemes.peek(), COMMA)) {
                    return new Definitions(identifiers);
                } else {
                    lexemes.pop();
                }
            }
        }
    }
}
