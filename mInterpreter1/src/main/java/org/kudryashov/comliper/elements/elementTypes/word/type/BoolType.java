package org.kudryashov.comliper.elements.elementTypes.word.type;

import org.kudryashov.comliper.elements.elementTypes.ElementName;
import org.kudryashov.comliper.elements.elementTypes.util.TypedProgramElement;
import org.kudryashov.comliper.elements.elementTypes.word.enumeration.Word;

import java.util.Collections;
import java.util.List;

import static org.kudryashov.comliper.elements.elementTypes.word.type.BoolType.Value.FALSE;
import static org.kudryashov.comliper.elements.elementTypes.word.type.BoolType.Value.TRUE;

public class BoolType extends VariableType {
    @Override
    public Word getWord() {
        return Word.BOOL;
    }

    public static Value get(ElementName name) {
        if (name.equals(Word.TRUE)) {
            return TRUE;
        } else if (name.equals(Word.FALSE)) {
            return FALSE;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public enum Value implements TypedProgramElement {

        TRUE {
            @Override
            public List<ElementName> toPoliz() {
                return Collections.singletonList(Word.TRUE);
            }
        },
        FALSE {
            @Override
            public List<ElementName> toPoliz() {
                return Collections.singletonList(Word.FALSE);
            }
        };

        @Override
        public VariableType type() {
            return VariableTypes.BOOL;
        }
    }
}
