package org.kudryashov.comliper.lexical.nodes;

import org.kudryashov.comliper.lexical.context.LexicalContext;
import org.kudryashov.comliper.lexical.rules.LexicalRule;

import java.util.List;

import static java.util.Arrays.asList;

public class CommentStartNode implements Node {

    private List<LexicalRule> rules = asList(new CommentEndRule(), new OtherRule());

    @Override
    public List<LexicalRule> getRules() {
        return rules;
    }

    private class CommentEndRule implements LexicalRule {

        @Override
        public boolean satisfied(LexicalContext context) {
            return context.currentCharacter() == '}';
        }

        @Override
        public Node apply(LexicalContext context) {
            context.readNextCharacter();
            return new StartNode();
        }
    }

    private class OtherRule implements LexicalRule {

        @Override
        public boolean satisfied(LexicalContext context) {
            return true;
        }

        @Override
        public Node apply(LexicalContext context) {
            context.readNextCharacter();
            return CommentStartNode.this;
        }
    }
}
