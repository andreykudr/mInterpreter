package org.kudryashov.comliper.syntax;

import org.kudryashov.comliper.ProgramAnalyzer;
import org.kudryashov.comliper.elements.elementTypes.ElementName;
import org.kudryashov.comliper.elements.elementTypes.ProgramElement;
import org.kudryashov.comliper.elements.elementTypes.util.Program;
import org.kudryashov.comliper.syntax.exception.SyntaxError;
import org.kudryashov.comliper.syntax.rule.ProgramRule;
import org.kudryashov.comliper.syntax.rule.SyntaxRule;

import java.util.List;

public class SyntaxAnalyzer implements ProgramAnalyzer {

    private ProgramRule programRule = new ProgramRule();

    @Override
    public ProgramElement analyze(List<ElementName> lexemes) {
        Context context = new Context(lexemes);
        Program program = programRule.apply(context);
        if (program != null) {
            return program;
        }
        throw new SyntaxError();
    }
}
