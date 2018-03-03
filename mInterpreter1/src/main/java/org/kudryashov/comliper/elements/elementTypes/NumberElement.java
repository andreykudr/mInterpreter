package org.kudryashov.comliper.elements.elementTypes;

import org.kudryashov.comliper.elements.elementTypes.util.TypedProgramElement;
import org.kudryashov.comliper.elements.elementTypes.word.type.VariableType;
import org.kudryashov.comliper.elements.elementTypes.word.type.VariableTypes;
import org.kudryashov.comliper.lexical.LexicalElement;

import java.util.List;
import java.util.Objects;

import static java.util.Collections.singletonList;

public class NumberElement extends LexicalElement implements TypedProgramElement {

    private Value value;
    private VariableType type;

    public NumberElement(Value value) {
        this.value = value;
        type = value.value instanceof Integer ? VariableTypes.INT : VariableTypes.FLOAT;
    }

    @Override
    public List<ElementName> toPoliz() {
        return singletonList(value);
    }

    public Number value() {
        return value.value;
    }

    public VariableType getVariableType() {
        return type;
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberElement that = (NumberElement) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public VariableType type() {
        return type;
    }

    public static class Value implements ElementName {
        private Number value;

        public Value(Number value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Value value1 = (Value) o;
            return Objects.equals(value, value1.value);
        }

        @Override
        public int hashCode() {

            return Objects.hash(value);
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }
}
