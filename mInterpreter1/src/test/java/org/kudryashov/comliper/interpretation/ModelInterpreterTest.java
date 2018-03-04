package org.kudryashov.comliper.interpretation;

import org.kudryashov.comliper.Instances;
import org.kudryashov.comliper.elements.elementTypes.Identifier;
import org.kudryashov.comliper.elements.elementTypes.NumberElement;
import org.kudryashov.comliper.elements.elementTypes.repositories.Repository;
import org.kudryashov.comliper.elements.elementTypes.util.poliz.GotoLabel;
import org.kudryashov.comliper.elements.elementTypes.util.poliz.PolizPointer;
import org.kudryashov.comliper.elements.elementTypes.word.type.VariableTypes;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static java.util.Arrays.asList;
import static org.kudryashov.comliper.elements.elementTypes.separator.Separator.Name.*;
import static org.kudryashov.comliper.elements.elementTypes.util.poliz.AdditionalPolizElements.GOTO;
import static org.kudryashov.comliper.elements.elementTypes.util.poliz.AdditionalPolizElements.REVERT_IF;
import static org.kudryashov.comliper.elements.elementTypes.word.enumeration.Word.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.assertEquals;

public class ModelInterpreterTest {

    private ModelInterpreter sut;
    private ByteArrayOutputStream output;
    private Repository<Identifier> identifiers;
    @Mock
    private BufferedReader reader;

    @BeforeMethod
    public void setup() {
        initMocks(this);
        output = new ByteArrayOutputStream();
        sut = new ModelInterpreter(output, reader);
        identifiers = Instances.get(Instances.IDENTIFIERS);
    }

    @Test
    public void shouldWriteResultOnOutputStream() {
        sut.interpret(asList(TRUE, WRITE), identifiers);
        assertEquals(output.toString(), "TRUE\n");
    }

    @Test
    public void shouldCalculateExpression() {
        sut.interpret(asList(new NumberElement.Value(1), new NumberElement.Value(2), PLUS, WRITE), identifiers);
        assertEquals(output.toString(), "3\n");
    }

    @Test
    public void shouldInterpretAs() {
        Identifier.Name var1name = defineVar1();
        sut.interpret(asList(var1name, new NumberElement.Value(1), AS, var1name, WRITE), identifiers);
        assertEquals(output.toString(), "1\n");
    }

    @Test
    public void shouldInterpretIf() {
        GotoLabel elseBranchLabel = new GotoLabel();
        GotoLabel endIfLabel = new GotoLabel();
        sut.interpret(asList(TRUE, new PolizPointer(elseBranchLabel), REVERT_IF, FALSE, WRITE, new PolizPointer(endIfLabel),
                GOTO, elseBranchLabel, TRUE, WRITE, endIfLabel), identifiers);
        assertEquals(output.toString(), "FALSE\n");
    }


    @Test
    public void shouldInterpretLess() {
        sut.interpret(asList(new NumberElement.Value(1), new NumberElement.Value(2), LESS, WRITE), identifiers);
        assertEquals(output.toString(), "TRUE\n");
    }

    @Test
    public void shouldInterpretMore() {
        sut.interpret(asList(new NumberElement.Value(1.2f), new NumberElement.Value(2), MORE, WRITE), identifiers);
        assertEquals(output.toString(), "FALSE\n");
    }

    @Test
    public void shoulInterpretMoreEqual() {
        sut.interpret(asList(new NumberElement.Value(1.2f), new NumberElement.Value(2), MORE_OR_EQUALS, WRITE), identifiers);
        assertEquals(output.toString(), "FALSE\n");
    }

    @Test
    public void shouldInterpretLessEqual() {
        sut.interpret(asList(new NumberElement.Value(1.2f), new NumberElement.Value(2), LESS_OR_EQUALS, WRITE), identifiers);
        assertEquals(output.toString(), "TRUE\n");
    }

    @Test
    public void shouldInterpretNotEqual() {
        sut.interpret(asList(new NumberElement.Value(1.2f), new NumberElement.Value(1.20f), NOT_EQUALS, WRITE), identifiers);
        assertEquals(output.toString(), "FALSE\n");
    }

    @Test
    public void shouldInterpretEqual() {
        sut.interpret(asList(new NumberElement.Value(1.2f), new NumberElement.Value(1.20f), EQUAL, WRITE), identifiers);
        assertEquals(output.toString(), "TRUE\n");
    }

    @Test
    public void shouldInterpretOr() {
        sut.interpret(asList(FALSE, TRUE, LOGIC_OR, WRITE), identifiers);
        assertEquals(output.toString(), "TRUE\n");
    }

    @Test
    public void shouldInterpretAnd() {
        sut.interpret(asList(TRUE, FALSE, LOGIC_AND, WRITE), identifiers);
        assertEquals(output.toString(), "FALSE\n");
    }

    @Test
    public void shouldInterpretPlus_WithInteger() {
        sut.interpret(asList(new NumberElement.Value(1), new NumberElement.Value(2), PLUS, WRITE), identifiers);
        assertEquals(output.toString(), "3\n");
    }

    @Test
    public void shouldInterpretPlus_WithFloat() {
        sut.interpret(asList(new NumberElement.Value(1.5f), new NumberElement.Value(2.1f), PLUS, WRITE), identifiers);
        assertEquals(output.toString(), "3.6\n");
    }

    @Test
    public void shouldInterpretMinus_WithInt() {
        sut.interpret(asList(new NumberElement.Value(1), new NumberElement.Value(2), MINUS, WRITE), identifiers);
        assertEquals(output.toString(), "-1\n");
    }

    @Test
    public void shouldInterpretMinus_WithFloat() {
        sut.interpret(asList(new NumberElement.Value(3.1f), new NumberElement.Value(2.1f), MINUS, WRITE), identifiers);
        assertEquals(output.toString(), "1.0\n");
    }

    @Test
    public void shouldDivideIntegers() {
        sut.interpret(asList(new NumberElement.Value(3), new NumberElement.Value(2), DIVIDE, WRITE), identifiers);
        assertEquals(output.toString(), "1\n");
    }

    @Test
    public void shouldDivideFloat() {
        sut.interpret(asList(new NumberElement.Value(3f), new NumberElement.Value(2), DIVIDE, WRITE), identifiers);
        assertEquals(output.toString(), "1.5\n");
    }

    @Test
    public void shouldMultiplyInt() {
        sut.interpret(asList(new NumberElement.Value(3), new NumberElement.Value(2), MULTIPLY, WRITE), identifiers);
        assertEquals(output.toString(), "6\n");
    }

    @Test
    public void shouldMultiplyFloat() {
        sut.interpret(asList(new NumberElement.Value(3f), new NumberElement.Value(1.5), MULTIPLY, WRITE), identifiers);
        assertEquals(output.toString(), "4.5\n");
    }

    @Test
    public void shouldInterpretFor() {
        Identifier.Name var1name = defineVar1();
        GotoLabel endOfForLabel = new GotoLabel();
        GotoLabel startOfExpressionLabel = new GotoLabel();
        sut.interpret(asList(var1name, new NumberElement.Value(0), AS, startOfExpressionLabel, var1name, new NumberElement.Value(10), LESS,
                new PolizPointer(endOfForLabel), REVERT_IF, var1name, WRITE, var1name, new NumberElement.Value(1), var1name,
                PLUS, AS, new PolizPointer(startOfExpressionLabel), GOTO, endOfForLabel), identifiers);
        assertEquals(output.toString(), "0\n1\n2\n3\n4\n5\n6\n7\n8\n9\n");
    }

    @Test
    public void shouldInterpretRead() throws IOException {
        when(reader.readLine()).thenReturn("111");

        Identifier.Name var1name = defineVar1();
        sut.interpret(asList(var1name, READ, var1name, WRITE), identifiers);
        assertEquals(output.toString(), "111\n");
    }

    private Identifier.Name defineVar1() {
        Identifier.Name var1name = new Identifier.Name("var1");
        Identifier var1 = identifiers.get(var1name);
        var1.define(VariableTypes.INT);
        return var1name;
    }
}