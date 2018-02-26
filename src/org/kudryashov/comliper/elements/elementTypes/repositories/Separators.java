package org.kudryashov.comliper.elements.elementTypes.repositories;

import org.kudryashov.comliper.elements.elementTypes.ElementName;
import org.kudryashov.comliper.elements.elementTypes.operator.*;
import org.kudryashov.comliper.elements.elementTypes.separator.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Separators implements Repository<Separator> {

    private final static Map<Separator.Name, Separator> map = new HashMap<>();
    static {
        put(new DotSeparator());
        put(new LeftBraceSeparator());
        put(new RightBraceSeparator());
        put(new SemicolonSeparator());
        put(new CommaSeparator());
        put(new ColonSeparator());
        put(new NotEqual());
        put(new LessOrEqual());
        put(new MoreOrEqual());
        put(new Plus());
        put(new Minus());
        put(new Multiply());
        put(new Divide());
        put(new LogicOr());
        put(new LogicAnd());
        put(new Equal());
        put(new Less());
        put(new More());
    }

    @Override
    public Separator get(ElementName name) {
        if (map.containsKey(name)) {
            return map.get(name);
        }
        throw new IllegalArgumentException(name + " not separator");
    }

    @Override
    public Collection<Separator> getAll() {
        return map.values();
    }

    private static void put(Separator separator) {
        map.put(separator.getName(), separator);
    }
}
