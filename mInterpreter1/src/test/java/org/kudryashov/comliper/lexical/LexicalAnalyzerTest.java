package org.kudryashov.comliper.lexical;

import org.kudryashov.comliper.elements.elementTypes.ElementName;
import org.kudryashov.comliper.elements.elementTypes.Identifier;
import org.kudryashov.comliper.elements.elementTypes.NumberElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.kudryashov.comliper.elements.elementTypes.separator.Separator.Name.*;
import static org.kudryashov.comliper.elements.elementTypes.word.enumeration.Word.*;
import static org.testng.AssertJUnit.assertEquals;

public class LexicalAnalyzerTest {

    private LexicalAnalyzer sut = new LexicalAnalyzer();

    @Test(dataProvider = "programs")
    public void shouldParseText(String text, List<LexicalElement> expected) {
        List<ElementName> result = sut.analyze(text);
        assertEquals(expected, result);
    }

    @DataProvider(name = "programs")
    private Object[][] getPrograms() {
        return new Object[][]{
                {PROGRAM_TEXT1, OUTPUT_1},
                {SEPARATOR_TEXT, SEPARATOR_OUTPUT},
                {NUMBERS_TEXT, NUMBES_OUTPUT}
        };
    }

    private static final String PROGRAM_TEXT1 = "{:)}" +
            "program var int message1, int message2 begin message1 as 12; message2 as 123; write(message1, message2); " +
            "end.";
    private static final List<ElementName> OUTPUT_1 = asList(
            PROGRAM, VAR, INT, new Identifier.Name("message1"), COMMA, INT, new Identifier.Name("message2"),
            BEGIN, new Identifier.Name("message1"), AS, new NumberElement.Value(12), SEMICOLON,
            new Identifier.Name("message2"), AS, new NumberElement.Value(123), SEMICOLON,
            WRITE, LEFTBRACE, new Identifier.Name("message1"), COMMA,
            new Identifier.Name("message2"), RIGHTBRACE, SEMICOLON, END, DOT);

    private static final String SEPARATOR_TEXT = ", ;: < <> <= > >= () + - * / ^ =.";
    private static final List<ElementName> SEPARATOR_OUTPUT = asList(
            COMMA, SEMICOLON, COLON, LESS,
            NOT_EQUALS, LESS_OR_EQUALS, MORE, MORE_OR_EQUALS,
            LEFTBRACE, RIGHTBRACE, PLUS, MINUS,
            MULTIPLY, DIVIDE, LOGIC_AND, EQUAL,
            DOT
    );

    private static final String NUMBERS_TEXT = "100 100b 100o 100d 1ABh 100.100 .";
    private static final List<ElementName> NUMBES_OUTPUT = asList(
            new NumberElement.Value(100), new NumberElement.Value(0b100),
            new NumberElement.Value(0100), new NumberElement.Value(100),
            new NumberElement.Value(0x1AB), new NumberElement.Value(100.1f),
            DOT);
}