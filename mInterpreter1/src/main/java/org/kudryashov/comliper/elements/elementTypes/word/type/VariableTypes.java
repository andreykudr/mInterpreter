package org.kudryashov.comliper.elements.elementTypes.word.type;

import org.kudryashov.comliper.elements.elementTypes.ElementName;
import org.kudryashov.comliper.elements.elementTypes.repositories.Repository;
import org.kudryashov.comliper.elements.elementTypes.word.enumeration.Word;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class VariableTypes implements Repository<VariableType> {

    private static final Map<Word, VariableType> TYPES = new HashMap<Word, VariableType>() {
        {
            put(Word.INT, new IntType());
            put(Word.FLOAT, new FloatType());
            put(Word.BOOL, new BoolType());
        }
    };

    public static final VariableType FLOAT = TYPES.get(Word.FLOAT);
    public static final VariableType INT = TYPES.get(Word.INT);
    public static final VariableType BOOL = TYPES.get(Word.BOOL);

    @Override
    public VariableType get(ElementName name) {
        if (!TYPES.containsKey(name)) {
            throw new IllegalArgumentException("unknown VariableType" + name);
        }
        return TYPES.get(name);
    }

    @Override
    public Collection<VariableType> getAll() {
        return TYPES.values();
    }
}
