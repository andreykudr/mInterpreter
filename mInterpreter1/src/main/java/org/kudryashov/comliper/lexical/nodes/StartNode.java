package org.kudryashov.comliper.lexical.nodes;

import org.kudryashov.comliper.elements.elementTypes.separator.Separator;
import org.kudryashov.comliper.lexical.context.LexicalContext;
import org.kudryashov.comliper.lexical.rules.LexicalRule;

import java.util.List;

import static java.lang.Character.*;
import static java.util.Arrays.asList;

public class StartNode implements Node {

    private List<LexicalRule> rules = asList(new SpacesRule(), new LetterRule(), new LessRule(), new MoreRule(), new OneSymbolSeparatorRule(),
            new CommentStartRule(), new NumberStartRule());

    @Override
    public List<LexicalRule> getRules() {
        return rules;
    }

    private class SpacesRule implements LexicalRule {

        @Override
        public boolean satisfied(LexicalContext context) {
            return isWhitespace(context.currentCharacter());
        }

        @Override
        public Node apply(LexicalContext context) {
            if (context.hasNextCharacter()) {
                context.readNextCharacter();
                return StartNode.this;
            } else {
                return new EndNode();
            }
        }
    }

    private class LetterRule implements LexicalRule {

        @Override
        public boolean satisfied(LexicalContext context) {
            return isLetter(context.currentCharacter());
        }

        @Override
        public Node apply(LexicalContext context) {
            prepareContext(context);
            return new IdentifierNode();
        }

        private void prepareContext(LexicalContext context) {
            context.clearBuffer();
            context.getBuffer().append(context.currentCharacter());
            context.readNextCharacter();
        }
    }

    private class OneSymbolSeparatorRule implements LexicalRule {

        @Override
        public boolean satisfied(LexicalContext context) {
            char current = context.currentCharacter();
            for (Separator.Name separator : Separator.Name.values()) {
                if (separator.getValue().equals(Character.toString(current))) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public Node apply(LexicalContext context) {
            return new SeparatorNode();
        }
    }

    private class LessRule implements LexicalRule {

        @Override
        public boolean satisfied(LexicalContext context) {
            return context.currentCharacter() == '<';
        }

        @Override
        public Node apply(LexicalContext context) {
            context.readNextCharacter();
            return new LessNode();
        }
    }

    private class MoreRule implements LexicalRule {

        @Override
        public boolean satisfied(LexicalContext context) {
            return context.currentCharacter() == '>';
        }

        @Override
        public Node apply(LexicalContext context) {
            context.readNextCharacter();
            return new MoreNode();
        }
    }

    private class CommentStartRule implements LexicalRule {

        @Override
        public boolean satisfied(LexicalContext context) {
            return context.currentCharacter() == '{';
        }

        @Override
        public Node apply(LexicalContext context) {
            return new CommentStartNode();
        }
    }

    private class NumberStartRule implements LexicalRule {

        @Override
        public boolean satisfied(LexicalContext context) {
            return isDigit(context.currentCharacter());
        }

        @Override
        public Node apply(LexicalContext context) {
            context.getBuffer().append(context.currentCharacter());
            context.readNextCharacter();
            return new NumberNode();
        }
    }
}
