package org.kudryashov.comliper.syntax.rule;

import org.kudryashov.comliper.elements.elementTypes.util.ComplexOperator;
import org.kudryashov.comliper.elements.elementTypes.util.Definitions;
import org.kudryashov.comliper.elements.elementTypes.util.Program;
import org.kudryashov.comliper.syntax.Context;
import org.kudryashov.comliper.syntax.rule.operator.OperatorsRule;

import static org.kudryashov.comliper.elements.elementTypes.separator.Separator.Name.DOT;
import static org.kudryashov.comliper.elements.elementTypes.word.enumeration.Word.*;

public class ProgramRule implements SyntaxRule<Program> {

    @Override
    public Program apply(Context lexemes) {
        if (check(lexemes.pop(), PROGRAM) && check(lexemes.pop(), VAR)) {
            Definitions definitions = new DefinitionsSyntaxRule().apply(lexemes);
            if (!definitions.isEmpty() && check(lexemes.pop(), BEGIN)) {
                ComplexOperator operators = new OperatorsRule().apply(lexemes);
                if (!operators.isEmpty() && check(lexemes.pop(), END) && check(lexemes.pop(), DOT)) {
                    return new Program(definitions, operators);
                }
            }
        }
        return null;
    }
}
