package org.kudryashov.comliper.semantyc;

import org.kudryashov.comliper.elements.elementTypes.ElementName;
import org.kudryashov.comliper.elements.elementTypes.word.type.VariableType;
import org.kudryashov.comliper.lexical.LexicalElement;
import org.kudryashov.comliper.elements.elementTypes.Identifier;
import org.kudryashov.comliper.syntax.Context;
import org.kudryashov.comliper.syntax.rule.DefinitionSyntaxRule;

public class DefinitionSemanticRule extends DefinitionSyntaxRule {

    @Override
    public Identifier apply(Context lexemes) {
        return null;
    }

    private boolean definitionCorrect(ElementName element, Identifier identifier) {
        if (!identifier.defined()) {
            identifier.define((VariableType) element);
            return true;
        } else {
            return false;
        }
    }
}
