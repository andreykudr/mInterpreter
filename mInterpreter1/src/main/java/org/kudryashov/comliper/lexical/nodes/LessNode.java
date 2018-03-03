package org.kudryashov.comliper.lexical.nodes;

import org.kudryashov.comliper.Instances;
import org.kudryashov.comliper.elements.elementTypes.repositories.Repository;
import org.kudryashov.comliper.elements.elementTypes.separator.Separator;
import org.kudryashov.comliper.lexical.context.LexicalContext;
import org.kudryashov.comliper.lexical.rules.LexicalRule;

import java.util.List;

import static java.util.Arrays.asList;
import static org.kudryashov.comliper.elements.elementTypes.separator.Separator.Name.LESS;
import static org.kudryashov.comliper.elements.elementTypes.separator.Separator.Name.LESS_OR_EQUALS;
import static org.kudryashov.comliper.elements.elementTypes.separator.Separator.Name.NOT_EQUALS;

public class LessNode implements Node {

    private final List<LexicalRule> rules = asList(new NotEqualRule(), new LessEqualRule(), new OtherRule());

    @Override
    public List<LexicalRule> getRules() {
        return rules;
    }

    private class NotEqualRule implements LexicalRule {

        @Override
        public boolean satisfied(LexicalContext context) {
            return context.currentCharacter() == '>';
        }

        @Override
        public Node apply(LexicalContext context) {
            context.readNextCharacter();
            context.putOutputElement(NOT_EQUALS);
            return new StartNode();
        }
    }

    private class LessEqualRule implements LexicalRule {

        @Override
        public boolean satisfied(LexicalContext context) {
            return context.currentCharacter() == '=';
        }

        @Override
        public Node apply(LexicalContext context) {
            context.readNextCharacter();
            context.putOutputElement(LESS_OR_EQUALS);
            return new StartNode();
        }
    }

    private class OtherRule implements LexicalRule {

        @Override
        public boolean satisfied(LexicalContext context) {
            char c = context.currentCharacter();
            return c != '>' && c != '=';
        }

        @Override
        public Node apply(LexicalContext context) {
            context.putOutputElement(LESS);
            context.readNextCharacter();
            return new StartNode();
        }
    }
}
