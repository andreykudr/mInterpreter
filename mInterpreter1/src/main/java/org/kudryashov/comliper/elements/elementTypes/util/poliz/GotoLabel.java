package org.kudryashov.comliper.elements.elementTypes.util.poliz;

import org.kudryashov.comliper.elements.elementTypes.ElementName;

import java.util.List;
import java.util.Objects;

public class GotoLabel implements ElementName {

    private long hash = System.nanoTime();

    public int polizIndex(List<ElementName> poliz) {
        return poliz.indexOf(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GotoLabel gotoLabel = (GotoLabel) o;
        return hash == gotoLabel.hash;
    }

    @Override
    public int hashCode() {

        return Objects.hash(hash);
    }
}
