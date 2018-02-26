package org.kudryashov.comliper.elements.elementTypes;

import org.kudryashov.comliper.elements.elementTypes.util.TypedProgramElement;
import org.kudryashov.comliper.elements.elementTypes.word.type.VariableType;
import org.kudryashov.comliper.lexical.LexicalElement;

import java.util.List;
import java.util.Objects;

import static java.util.Collections.singletonList;

public class Identifier extends LexicalElement implements TypedProgramElement {

    private Name name;
    private boolean defined = false;
    private VariableType type;
    private ElementName value;

    public Identifier(String value) {
        this.name = new Name(value);
    }

    public Identifier(Name name) {
        this.name = name;
    }

    @Override
    public List<ElementName> toPoliz() {
        return singletonList(name);
    }

    public boolean defined() {
        return defined;
    }

    public void define(VariableType type) {
        this.type = type;
        defined = true;
    }

    public VariableType getVariableType() {
        return type;
    }

    public void setValue(ElementName value) {
        this.value = value;
    }

    public ElementName getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Identifier && equalValue((Identifier) o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    private boolean equalValue(Identifier id) {
        if (id.name == null) {
            if (this.name == null) {
                return true;
            } else {
                return false;
            }
        } else {
            return (id.name.equals(this.name));
        }
    }

    @Override
    public String toString() {
        return "Identifier{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public VariableType type() {
        return type;
    }

    public static class Name implements ElementName {
        private String value;

        public Name(String value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Name name1 = (Name) o;
            return Objects.equals(value, name1.value);
        }

        @Override
        public int hashCode() {

            return Objects.hash(value);
        }
    }
}
