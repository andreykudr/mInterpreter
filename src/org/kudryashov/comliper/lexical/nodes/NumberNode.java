package org.kudryashov.comliper.lexical.nodes;

import org.kudryashov.comliper.elements.elementTypes.NumberElement;
import org.kudryashov.comliper.lexical.context.LexicalContext;
import org.kudryashov.comliper.lexical.rules.LexicalRule;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Character.isDigit;
import static java.lang.Character.isSpaceChar;
import static java.util.Arrays.asList;
import static org.kudryashov.comliper.elements.elementTypes.word.type.VariableTypes.INT;

public class NumberNode implements Node {

    private final Set<Character> availableSymbols = new HashSet<Character>() {
        {
            add('.');
            add('b');
            add('B');
            add('o');
            add('O');
            add('d');
            add('D');
            add('h');
            add('H');
        }
    };

    private final List<LexicalRule> rules = asList(new DigitRule(), new SpacesRule(), new BinaryNumberRule(), new OctalRule(),
            new DecimalRule(), new HexRule(), new FloatNumberRule(), new OtherSymbolRule());

    @Override
    public List<LexicalRule> getRules() {
        return rules;
    }

    private class SpacesRule implements LexicalRule {

        @Override
        public boolean satisfied(LexicalContext context) {
            return isSpaceChar(context.currentCharacter());
        }

        @Override
        public Node apply(LexicalContext context) {
            context.putOutputElement(new NumberElement.Value(getValue(context)));
            context.clearBuffer();
            context.readNextCharacter();
            return new StartNode();
        }

        private Number getValue(LexicalContext context) {
            return Integer.valueOf(context.getBuffer().toString());
        }
    }

    private class BinaryNumberRule implements LexicalRule {

        @Override
        public boolean satisfied(LexicalContext context) {
            char c = context.currentCharacter();
            return c == 'b' || c == 'B';
        }

        @Override
        public Node apply(LexicalContext context) {
            int value = Integer.parseInt(context.getBuffer().toString(), 2);
            context.clearBuffer();
            context.putOutputElement(new NumberElement.Value(value));
            context.readNextCharacter();
            return new StartNode();
        }
    }

    private class OctalRule implements LexicalRule {

        @Override
        public boolean satisfied(LexicalContext context) {
            char c = context.currentCharacter();
            return c == 'o' || c == 'O';
        }

        @Override
        public Node apply(LexicalContext context) {
            int value = Integer.parseInt(context.getBuffer().toString(), 8);
            context.clearBuffer();
            context.putOutputElement(new NumberElement.Value(value));
            context.readNextCharacter();
            return new StartNode();
        }
    }

    private class DecimalRule implements LexicalRule {

        @Override
        public boolean satisfied(LexicalContext context) {
            char c = context.currentCharacter();
            return c == 'd' || c == 'D';
        }

        @Override
        public Node apply(LexicalContext context) {
            int value = Integer.parseInt(context.getBuffer().toString(), 10);
            context.clearBuffer();
            context.putOutputElement(new NumberElement.Value(value));
            context.readNextCharacter();
            return new StartNode();
        }
    }

    private class HexRule implements LexicalRule {

        @Override
        public boolean satisfied(LexicalContext context) {
            char c = context.currentCharacter();
            return c == 'h' || c == 'H';
        }

        @Override
        public Node apply(LexicalContext context) {
            int value = Integer.parseInt(context.getBuffer().toString(), 16);
            context.clearBuffer();
            context.putOutputElement(new NumberElement.Value(value));
            context.readNextCharacter();
            return new StartNode();
        }
    }

    private class DigitRule implements LexicalRule {

        @Override
        public boolean satisfied(LexicalContext context) {
            char c = context.currentCharacter();
            return isDigit(c) || c == 'A' || c == 'B' || c == 'C' || c == 'D' || c == 'E' || c == 'F';
        }

        @Override
        public Node apply(LexicalContext context) {
            context.getBuffer().append(context.currentCharacter());
            context.readNextCharacter();
            return NumberNode.this;
        }

    }

    private class FloatNumberRule implements LexicalRule {

        @Override
        public boolean satisfied(LexicalContext context) {
            return context.currentCharacter() == '.' && context.hasNextCharacter();
        }

        @Override
        public Node apply(LexicalContext context) {
            context.getBuffer().append(".");
            context.readNextCharacter();
            return new FloatNumberNode();
        }
    }

    private class OtherSymbolRule implements LexicalRule {

        @Override
        public boolean satisfied(LexicalContext context) {
            char c = context.currentCharacter();
            return !isDigit(c) && !availableSymbols.contains(c);
        }

        @Override
        public Node apply(LexicalContext context) {
            context.putOutputElement(new NumberElement.Value(getValue(context)));
            context.clearBuffer();
            return new StartNode();
        }

        private Number getValue(LexicalContext context) {
            return Integer.valueOf(context.getBuffer().toString());
        }
    }
}
