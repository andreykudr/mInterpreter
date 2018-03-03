package org.kudryashov.comliper.elements.elementTypes.repositories;

import org.kudryashov.comliper.Instances;
import org.kudryashov.comliper.elements.elementTypes.ElementName;
import org.kudryashov.comliper.elements.elementTypes.operator.*;
import org.kudryashov.comliper.elements.elementTypes.word.*;
import org.kudryashov.comliper.elements.elementTypes.word.enumeration.Word;
import org.kudryashov.comliper.elements.elementTypes.word.type.VariableType;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Words implements Repository<ReservedWord> {

    private final static Map<Word, ReservedWord> words = new HashMap<>();

    private final static Repository<VariableType> TYPES = Instances.get(Instances.VARIABLE_TYPES);

    static {
        /*put(new ProgramWord());
        put(new VarWord());
        putTypes();
        put(new BeginWord());
        put(new EndWord());
        put(new IfOperator());
        put(new ThenWord());
        put(new ElseWord());
        put(new WhileOperator());
        put(new DoWord());
        put(new ForOperator());
        put(new ToWord());
        put(new ReadOperator());
        put(new WriteOperator());
        put(new TrueWord());
        put(new FalseWord());
        put(new NotOperator());
        put(new AsOperator());*/
    }

    private static void putTypes() {
        for (VariableType type : TYPES.getAll()) {

            put(type);
        }
    }

    @Override
    public ReservedWord get(ElementName name) {
        return words.get(name);
    }

    @Override
    public Collection<ReservedWord> getAll() {
        return words.values();
    }

    private static void put(ReservedWord word) {
        words.put(word.getWord(), word);
    }
}
