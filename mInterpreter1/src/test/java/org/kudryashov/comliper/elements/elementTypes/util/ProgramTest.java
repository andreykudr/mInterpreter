package org.kudryashov.comliper.elements.elementTypes.util;

import org.kudryashov.comliper.elements.elementTypes.ElementName;
import org.kudryashov.comliper.elements.elementTypes.Identifier;
import org.kudryashov.comliper.elements.elementTypes.NumberElement;
import org.kudryashov.comliper.elements.elementTypes.ProgramElement;
import org.kudryashov.comliper.elements.elementTypes.util.poliz.PolizElementNumber;
import org.kudryashov.comliper.syntax.SemanticAnalyzer;
import org.testng.annotations.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.kudryashov.comliper.elements.elementTypes.separator.Separator.Name.LESS;
import static org.kudryashov.comliper.elements.elementTypes.separator.Separator.Name.PLUS;
import static org.kudryashov.comliper.elements.elementTypes.util.poliz.AdditionalPolizElements.GOTO;
import static org.kudryashov.comliper.elements.elementTypes.util.poliz.AdditionalPolizElements.REVERT_IF;
import static org.kudryashov.comliper.elements.elementTypes.word.enumeration.Word.*;
import static org.testng.Assert.assertEquals;

public class ProgramTest {

    private final SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();

    @Test
    public void shouldGeneratePolizForWriteAndAs() {
        ProgramElement sut = semanticAnalyzer.analyze("program var int v1 begin v1 as 1; v1 as v1 + 2; " +
                "write(1 < v1); write(not true) end.");
        Identifier.Name v1 = new Identifier.Name("v1");
        NumberElement.Value oneNumber = new NumberElement.Value(1);
        List<ElementName> expected = asList(v1, oneNumber, AS,
                v1, v1, new NumberElement.Value(2), PLUS, AS, oneNumber, v1, LESS, WRITE, TRUE, NOT, WRITE);
        assertEquals(sut.toPoliz(), expected);
    }

    @Test
    public void shouldGeneratePolizForFullIf() {
        ProgramElement sut = new SemanticAnalyzer().analyze("program var int v1 begin" +
                " if true then write(false) else write(true) end.");
        Identifier.Name v1 = new Identifier.Name("v1");
        List<ElementName> expected = asList(TRUE, new PolizElementNumber(7), REVERT_IF, FALSE, WRITE, new PolizElementNumber(9), GOTO, TRUE, WRITE);
        assertEquals(sut.toPoliz(), expected);
    }

    @Test
    public void shouldGeneratePolizForNotFullIf() {
        ProgramElement sut = new SemanticAnalyzer().analyze("program var int v1 begin if true then write(true) end.");
        List<ElementName> expected = asList(TRUE, new PolizElementNumber(7), REVERT_IF, TRUE, WRITE, new PolizElementNumber(7), GOTO);
        assertEquals(sut.toPoliz(), expected);
    }

    @Test
    public void shouldGeneratePolizForFor() {
        ProgramElement sut = new SemanticAnalyzer().analyze("program var int i begin for i as 0 to i < 10 do write(i) end.");
        Identifier.Name i = new Identifier.Name("i");
        List<ElementName> expected = asList(i, new NumberElement.Value(0), AS, i, new NumberElement.Value(10), LESS,
                new PolizElementNumber(12), REVERT_IF, i, WRITE, new PolizElementNumber(3), GOTO);
        assertEquals(sut.toPoliz(), expected);
    }

    @Test
    public void shouldGeneratePolizForWhile() {
        ProgramElement sut = new SemanticAnalyzer().analyze("program var int i begin while i < 10 do write(i) end.");
        Identifier.Name i = new Identifier.Name("i");
        List<ElementName> expected = asList(i, new NumberElement.Value(10), LESS,
                new PolizElementNumber(9), REVERT_IF, i, WRITE, new PolizElementNumber(0), GOTO);
        assertEquals(sut.toPoliz(), expected);
    }
}