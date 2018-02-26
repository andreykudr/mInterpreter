package org.kudryashov.comliper.syntax;

import org.kudryashov.comliper.Instances;
import org.kudryashov.comliper.elements.elementTypes.Identifier;
import org.kudryashov.comliper.elements.elementTypes.ProgramElement;
import org.kudryashov.comliper.elements.elementTypes.repositories.Repository;
import org.kudryashov.comliper.elements.elementTypes.util.Program;
import org.kudryashov.comliper.lexical.LexicalAnalyzer;
import org.kudryashov.comliper.semantyc.exception.SemanticExpressionError;

import static org.kudryashov.comliper.Instances.IDENTIFIERS;

public class SemanticAnalyzer {

    private final Repository<Identifier> identifiers = Instances.get(IDENTIFIERS);
    private LexicalAnalyzer lexical = new LexicalAnalyzer();
    private SyntaxAnalyzer syntax = new SyntaxAnalyzer();

    public ProgramElement analyze(String text) {
        ProgramElement element = syntax.analyze(lexical.analyze(text));
        if (element == null || !identifiersDefined()) {
            throw new SemanticExpressionError();
        }
        return element;
    }

    private boolean identifiersDefined() {
        for (Identifier identifier : identifiers.getAll()) {
            if (!identifier.defined()) {
                return false;
            }
        }
        return true;
    }
}
