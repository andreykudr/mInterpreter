package org.kudryashov.comliper;

import org.kudryashov.comliper.elements.elementTypes.ElementName;
import org.kudryashov.comliper.elements.elementTypes.ProgramElement;

import java.util.List;

public interface ProgramAnalyzer {

    ProgramElement analyze(List<ElementName> lexemes);
}
