package org.kudryashov.comliper.elements.elementTypes.util.poliz;

import org.kudryashov.comliper.elements.elementTypes.ElementName;

public class PolizPointer implements ElementName {

    public GotoLabel label;



    public PolizPointer(GotoLabel label) {
        this.label = label;
    }
}
