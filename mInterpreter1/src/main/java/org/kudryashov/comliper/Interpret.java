package org.kudryashov.comliper;

import org.kudryashov.comliper.elements.elementTypes.Identifier;
import org.kudryashov.comliper.elements.elementTypes.ProgramElement;
import org.kudryashov.comliper.elements.elementTypes.repositories.Repository;
import org.kudryashov.comliper.interpretation.Interpreter;
import org.kudryashov.comliper.interpretation.ModelInterpreter;
import org.kudryashov.comliper.syntax.SemanticAnalyzer;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Interpret {

    private static SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
    private static Interpreter interpreter = new ModelInterpreter(
            System.out,
            new BufferedReader(new InputStreamReader(System.in))
    );
    private static Repository<Identifier> identifiers = Instances.get(Instances.IDENTIFIERS);

    public static void main(String... args) {
        ProgramElement program = semanticAnalyzer.analyze(getText(args));
        interpreter.interpret(program.toPoliz(), identifiers);
        Instances.reset();
    }

    private static String getText(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("should be 1 argument = program text");
        }
        return args[0];
    }

    public static void setInterpreter(Interpreter interpreter) {
        Interpret.interpreter = interpreter;
    }

    public static void setSemanticAnalyzer(SemanticAnalyzer semanticAnalyzer) {
        Interpret.semanticAnalyzer = semanticAnalyzer;
    }

    public static void setIdentifiers(Repository<Identifier> identifiers) {
        Interpret.identifiers = identifiers;
    }
}
