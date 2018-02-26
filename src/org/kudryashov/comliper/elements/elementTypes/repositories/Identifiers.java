package org.kudryashov.comliper.elements.elementTypes.repositories;

import org.kudryashov.comliper.elements.elementTypes.ElementName;
import org.kudryashov.comliper.elements.elementTypes.Identifier;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Identifiers implements Repository<Identifier> {

    private final Map<ElementName, Identifier> identifiers = new HashMap<>();

    @Override
    public Identifier get(ElementName name) {
        if (!identifiers.containsKey(name)) {
            Identifier newInstance = new Identifier((Identifier.Name)name);
            identifiers.put(name, newInstance);
        }
        return identifiers.get(name);
    }

    @Override
    public Collection<Identifier> getAll() {
        return identifiers.values();
    }
}
