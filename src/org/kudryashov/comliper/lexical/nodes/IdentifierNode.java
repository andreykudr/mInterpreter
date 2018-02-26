package org.kudryashov.comliper.lexical.nodes;

import org.kudryashov.comliper.elements.elementTypes.Identifier;
import org.kudryashov.comliper.elements.elementTypes.word.enumeration.Word;
import org.kudryashov.comliper.lexical.context.LexicalContext;
import org.kudryashov.comliper.lexical.rules.LexicalRule;

import java.util.Arrays;
import java.util.List;

import static java.lang.Character.isLetterOrDigit;

public class IdentifierNode implements Node {

    private List<LexicalRule> rules = Arrays.asList(new LetterOrNumberRule(), new IsWordRule(), new IsIdentifier());

    @Override
    public List<LexicalRule> getRules() {
        return rules;
    }

    private class LetterOrNumberRule implements LexicalRule {

        @Override
        public boolean satisfied(LexicalContext context) {
            char current = context.currentCharacter();
            return isLetterOrDigit(current);
        }

        @Override
        public Node apply(LexicalContext context) {
            char c = context.currentCharacter();
            context.getBuffer().append(c);
            if (context.hasNextCharacter()) {
                context.readNextCharacter();
                return IdentifierNode.this;
            }
            return new EndNode();
        }
    }

    private class IsWordRule implements LexicalRule {

        @Override
        public boolean satisfied(LexicalContext context) {
            String identifier = context.getBuffer().toString().toUpperCase();
            for (Word word : Word.values()) {
                if (word.name().equals(identifier)) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public Node apply(LexicalContext context) {
            putWord(context);
            return new StartNode();
        }

        private void putWord(LexicalContext context) {
            String identifier = context.getBuffer().toString().toUpperCase();
            context.putOutputElement(Word.valueOf(identifier));
            context.clearBuffer();
        }
    }

    private class IsIdentifier implements LexicalRule {

        @Override
        public boolean satisfied(LexicalContext context) {
            return context.getBuffer().length() != 0;
        }

        @Override
        public Node apply(LexicalContext context) {
            String name = context.getBuffer().toString();
            context.putOutputElement(new Identifier.Name(name));
            context.clearBuffer();
            return new StartNode();
        }
    }
}
