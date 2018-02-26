package org.kudryashov.comliper.syntax;

import org.kudryashov.comliper.Instances;
import org.kudryashov.comliper.elements.elementTypes.ElementName;
import org.kudryashov.comliper.elements.elementTypes.Identifier;
import org.kudryashov.comliper.elements.elementTypes.NumberElement;
import org.kudryashov.comliper.elements.elementTypes.repositories.Repository;
import org.kudryashov.comliper.elements.elementTypes.separator.*;
import org.kudryashov.comliper.elements.elementTypes.word.*;
import org.kudryashov.comliper.elements.elementTypes.word.enumeration.Word;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.kudryashov.comliper.elements.elementTypes.separator.Separator.Name.*;
import static org.kudryashov.comliper.elements.elementTypes.word.enumeration.Word.*;

public class SyntaxAnalyzerTest {


    private final static Repository<Separator> separators = Instances.get(Instances.SEPARATORS);
    private final static Repository<ReservedWord> words = Instances.get(Instances.WORDS);

    private SyntaxAnalyzer sut = new SyntaxAnalyzer();

    @Test(expectedExceptions = RuntimeException.class, dataProvider = "withErrors")
    public void shouldBeFail(List<ElementName> lexemes) {
        sut.analyze(lexemes);
    }

    @Test(dataProvider = "correct")
    public void shouldBeSuccess(List<ElementName> lexemes) {
        sut.analyze(lexemes);
    }

    @DataProvider
    public static Object[][] withErrors() {
        Identifier var1 = new Identifier("var1");
        return new Object[][]{
                {asList(PROGRAM, END, separators.get(DOT))},
                {asList(PROGRAM, VAR, var1, var1, BEGIN, END, separators.get(DOT))},
                {asList(PROGRAM, VAR, Word.INT, var1, BEGIN, END, separators.get(DOT))},
                {asList(PROGRAM, VAR, Word.INT, var1, COMMA, INT, var1, BEGIN, END, separators.get(DOT))},
                {asList(PROGRAM, VAR, Word.INT, var1, BEGIN, WRITE, LEFTBRACE, var1, MULTIPLY, FALSE, RIGHTBRACE, END, separators.get(DOT))}
        };
    }

    @DataProvider
    public static Object[][] correct() {
        return new Object[][]{
                {getProgram1()},
                {getProgram2()},
                {getProgram3()},
                {getProgram4()}
        };
    }

    private static List<ElementName> getProgram1() {
        Identifier.Name var1 = new Identifier.Name("var1");
        Identifier.Name var2 = new Identifier.Name("var2");
        return asList(PROGRAM, VAR, INT, var1, COMMA, FLOAT, var2, BEGIN, var1, AS, new NumberElement.Value(1), END, DOT);
        }

    private static List<ElementName> getProgram2() {
        Identifier.Name var1 = new Identifier.Name("var1");
        Identifier.Name var2 = new Identifier.Name("var2");
        return asList(PROGRAM, VAR, INT, var1, COMMA, FLOAT, var2, BEGIN, var1, AS, new NumberElement.Value(1), SEMICOLON,
                var2, AS, new NumberElement.Value(1.0), SEMICOLON, var2, AS, new NumberElement.Value(2.0), SEMICOLON,
                WRITE, LEFTBRACE, var1, COMMA, var2, COMMA, NOT, TRUE, RIGHTBRACE, END, DOT);
    }

    private static List<ElementName> getProgram4() {
        Identifier.Name var1 = new Identifier.Name("var1");
        return asList(PROGRAM, VAR, INT, var1, BEGIN, WRITE, LEFTBRACE, new NumberElement.Value(1), MULTIPLY,
                new NumberElement.Value(0), PLUS, var1, LESS, var1, RIGHTBRACE, END,DOT);
    }

    private static List<ElementName> getProgram3() {
        Identifier.Name n = new Identifier.Name("n");
        Identifier.Name factorial = new Identifier.Name("factorial");
        Identifier.Name i = new Identifier.Name("i");
        NumberElement.Value number1 = new NumberElement.Value(1);
        return asList(PROGRAM, VAR, INT, n, COMMA, INT, factorial, COMMA, INT, i, BEGIN, READ, LEFTBRACE, n, RIGHTBRACE,
                SEMICOLON, factorial, AS, number1, SEMICOLON, FOR, i, AS, number1, TO, i, LESS, factorial, DO, BEGIN,factorial, AS,
                factorial, PLUS, i, SEMICOLON, i, AS, i, PLUS, number1, END, SEMICOLON, WRITE, LEFTBRACE, factorial, RIGHTBRACE,
                END, DOT);
    }
}