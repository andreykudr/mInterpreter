package org.kudryashov.comliper.elements.elementTypes.util.poliz;

import org.kudryashov.comliper.elements.elementTypes.ElementName;

public class PolizElementNumber implements ElementName {

    public int number;

    public PolizElementNumber() {
    }

    public PolizElementNumber(int number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof PolizElementNumber
                && ((PolizElementNumber) o).number == this.number;
    }
}
