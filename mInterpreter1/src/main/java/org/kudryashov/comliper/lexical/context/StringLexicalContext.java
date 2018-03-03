package org.kudryashov.comliper.lexical.context;

import org.kudryashov.comliper.elements.elementTypes.ElementName;
import org.kudryashov.comliper.lexical.exception.UnexpectedEndOfInput;

import java.util.ArrayList;
import java.util.List;

public class StringLexicalContext implements LexicalContext {

    private final String text;
    private int index = 0;
    private List<ElementName> output = new ArrayList<>();
    private StringBuffer identifierBuffer = new StringBuffer();

    public StringLexicalContext(String text) {
        if (text == null) {
            throw new IllegalArgumentException("text can not be null");
        }
        this.text = text;
    }

    @Override
    public char currentCharacter() {
        return text.charAt(index);
    }

    @Override
    public boolean hasNextCharacter() {
        return text.length() >= index + 1;
    }

    @Override
    public void readNextCharacter() {
        if (!hasNextCharacter()) {
            throw new UnexpectedEndOfInput();
        }
        ++index;
    }

    @Override
    public void putOutputElement(ElementName element) {
        if (element == null) {
            throw new IllegalArgumentException("output element can not be null");
        }
        output.add(element);
    }

    @Override
    public List<ElementName> getOutput() {
        return output;
    }

    @Override
    public void clearBuffer() {
        identifierBuffer = new StringBuffer();
    }

    @Override
    public StringBuffer getBuffer() {
        return identifierBuffer;
    }
}
