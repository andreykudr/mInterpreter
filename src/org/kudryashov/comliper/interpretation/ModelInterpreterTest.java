package org.kudryashov.comliper.interpretation;

import org.kudryashov.comliper.Instances;
import org.kudryashov.comliper.elements.elementTypes.Identifier;
import org.kudryashov.comliper.elements.elementTypes.NumberElement;
import org.kudryashov.comliper.elements.elementTypes.repositories.Repository;
import org.kudryashov.comliper.elements.elementTypes.util.poliz.PolizElementNumber;
import org.kudryashov.comliper.elements.elementTypes.word.type.VariableTypes;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;

import static java.util.Arrays.asList;
import static org.kudryashov.comliper.elements.elementTypes.separator.Separator.Name.*;
import static org.kudryashov.comliper.elements.elementTypes.util.poliz.AdditionalPolizElements.GOTO;
import static org.kudryashov.comliper.elements.elementTypes.util.poliz.AdditionalPolizElements.REVERT_IF;
import static org.kudryashov.comliper.elements.elementTypes.word.enumeration.Word.*;
import static org.testng.Assert.assertEquals;

public class ModelInterpreterTest {

    private ModelInterpreter sut;
    private ByteArrayOutputStream output;
    private Repository<Identifier> identifiers;


    @BeforeMethod
    public void setup() {
        output = new ByteArrayOutputStream();
        sut = new ModelInterpreter(output);
        identifiers = Instances.get(Instances.IDENTIFIERS);
    }

    @Test
    public void shouldWriteResultOnOutputStream() {
        sut.interpret(asList(TRUE, WRITE), identifiers);
        assertEquals(output.toString(), "TRUE");
    }

    @Test
    public void shouldCalculateExpression() {
        sut.interpret(asList(new NumberElement.Value(1), new NumberElement.Value(2), PLUS, WRITE), identifiers);
        assertEquals(output.toString(), "3");
    }

    @Test
    public void shouldInterpretAs() {
        Identifier.Name var1name = new Identifier.Name("var1");
        Identifier var1 = identifiers.get(var1name);
        var1.define(VariableTypes.INT);
        sut.interpret(asList(var1name, new NumberElement.Value(1), AS, var1name, WRITE), identifiers);
        assertEquals(output.toString(), "1");
    }

    @Test
    public void shouldInterpretIf() {
        sut.interpret(asList(TRUE, new PolizElementNumber(7), REVERT_IF, FALSE, WRITE, new PolizElementNumber(9),
                GOTO, TRUE, WRITE), identifiers);
        assertEquals(output.toString(), "FALSE");
    }

    @Test
    public void shouldInterpretIfWithElseBranch() {
        sut.interpret(asList(FALSE, new PolizElementNumber(7), REVERT_IF, FALSE, WRITE, new PolizElementNumber(9),
                GOTO, TRUE, WRITE), identifiers);
        assertEquals(output.toString(), "TRUE");

    }

    @Test
    public void shouldInterpretLess() {
        sut.interpret(asList(new NumberElement.Value(1), new NumberElement.Value(2), LESS, WRITE), identifiers);
        assertEquals(output.toString(), "TRUE");
    }

    @Test
    public void shouldInterpretPlus_WithInteger() {
        sut.interpret(asList(new NumberElement.Value(1), new NumberElement.Value(2), PLUS, WRITE), identifiers);
        assertEquals(output.toString(), "3");
    }

    @Test
    public void shouldInterpretPlus_WithFloat() {
        sut.interpret(asList(new NumberElement.Value(1.5f), new NumberElement.Value(2.1f), PLUS, WRITE), identifiers);
        assertEquals(output.toString(), "3.6");
    }

    @Test
    public void shouldInterpretMinus_WithInt() {
        sut.interpret(asList(new NumberElement.Value(1), new NumberElement.Value(2), MINUS, WRITE), identifiers);
        assertEquals(output.toString(), "-1");
    }

    @Test
    public void shouldInterpretMinus_WithFloat() {
        sut.interpret(asList(new NumberElement.Value(3.1f), new NumberElement.Value(2.1f), MINUS, WRITE), identifiers);
        assertEquals(output.toString(), "1.0");
    }

    @Test
    public void shouldInterpretFor() {
        Identifier.Name var1name = new Identifier.Name("var1");
        Identifier var1 = identifiers.get(var1name);
        var1.define(VariableTypes.INT);
        sut.interpret(asList(var1name, new NumberElement.Value(0), AS, var1name, new NumberElement.Value(10), LESS,
                new PolizElementNumber(18), REVERT_IF, var1name, WRITE, var1name, new NumberElement.Value(1), var1name,
                PLUS, AS, new PolizElementNumber(3), GOTO), identifiers);
        assertEquals(output.toString(), "0123456789");
    }
}