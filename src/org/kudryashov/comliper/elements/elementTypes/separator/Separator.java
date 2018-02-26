package org.kudryashov.comliper.elements.elementTypes.separator;

import org.kudryashov.comliper.elements.elementTypes.ElementName;
import org.kudryashov.comliper.lexical.LexicalElement;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;

public abstract class Separator extends LexicalElement {

    public abstract Name getName();

    public enum Name implements ElementName {

        DOT("."),
        LEFTBRACE("("),
        RIGHTBRACE(")"),
        SEMICOLON(";"),
        COMMA(","),
        COLON(":"),
        NOT_EQUALS("<>"),
        LESS_OR_EQUALS("<="),
        MORE_OR_EQUALS(">="),
        PLUS("+"),
        MINUS("-"),
        MULTIPLY("*"),
        DIVIDE("/"),
        LOGIC_OR("\uF0DA"),
        LOGIC_AND("^"),
        EQUAL("="),
        LESS("<"),
        MORE(">");

        private String value;

        Name(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Separator{" +
                    "value='" + value + '\'' +
                    '}';
        }
    }

    public List<ElementName> toPoliz() {
        return asList(getName());
    }
}
