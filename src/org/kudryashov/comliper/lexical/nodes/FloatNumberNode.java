package org.kudryashov.comliper.lexical.nodes;

import org.kudryashov.comliper.elements.elementTypes.NumberElement;
import org.kudryashov.comliper.lexical.context.LexicalContext;
import org.kudryashov.comliper.lexical.rules.LexicalRule;

import java.util.List;

import static java.lang.Character.isDigit;
import static java.lang.Character.isSpaceChar;
import static java.util.Arrays.asList;
import static org.kudryashov.comliper.elements.elementTypes.word.type.VariableTypes.FLOAT;

public class FloatNumberNode implements Node {

    private List<LexicalRule> rules = asList(new DigitRule(), new SpacesRule(), new OtherRule());

    @Override
    public List<LexicalRule> getRules() {
        return rules;
    }

    private class DigitRule implements LexicalRule {

        @Override
        public boolean satisfied(LexicalContext context) {
            return isDigit(context.currentCharacter());
        }

        @Override
        public Node apply(LexicalContext context) {
            context.getBuffer().append(context.currentCharacter());
            context.readNextCharacter();
            return FloatNumberNode.this;
        }
    }

    private class SpacesRule implements LexicalRule {

        @Override
        public boolean satisfied(LexicalContext context) {
            return isSpaceChar(context.currentCharacter());
        }

        @Override
        public Node apply(LexicalContext context) {
            Float value = Float.valueOf(context.getBuffer().toString());
            context.putOutputElement(new NumberElement.Value(value));
            context.clearBuffer();
            context.readNextCharacter();
            return new StartNode();
        }
    }

    private class OtherRule implements LexicalRule {

        @Override
        public boolean satisfied(LexicalContext context) {
            char c = context.currentCharacter();
            return !isDigit(c) && !isSpaceChar(c);
        }

        @Override
        public Node apply(LexicalContext context) {
            throw new IllegalArgumentException("unexpected number character " + context.currentCharacter());
        }
    }
}
