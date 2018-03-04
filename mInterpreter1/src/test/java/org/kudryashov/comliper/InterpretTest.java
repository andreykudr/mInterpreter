package org.kudryashov.comliper;

import org.kudryashov.comliper.interpretation.ModelInterpreter;
import org.kudryashov.comliper.syntax.SemanticAnalyzer;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class InterpretTest {

    @Mock
    private BufferedReader input;

    @BeforeMethod
    public void reset() {
        initMocks(this);

        Instances.reset();
        Interpret.setInterpreter(
                new ModelInterpreter(
                    System.out,
                    input
        ));
        Interpret.setSemanticAnalyzer(new SemanticAnalyzer());
        Interpret.setIdentifiers(Instances.get(Instances.IDENTIFIERS));
    }

    @Test
    public void helloWorldTest() {
        String text = "{ \n" +
                "    It is 123 program on M language\n" +
                "\tsuch Hello world program, but there is not Strings :)\n" +
                "}\n" +
                "program \n" +
                "\n" +
                "var int message1, int message2\n" +
                "\n" +
                "begin\n" +
                "\tmessage1 as 12;\n" +
                "\tmessage2 as 3;\n" +
                "\t\n" +
                "\twrite(message1);\n" +
                "\twrite(message2)\n" +
                "end.";
        Interpret.main(text);
    }

    @Test
    public void factorialTest() throws IOException {
        when(input.readLine()).thenReturn("5");

        String text = "{ \n" +
                "    It is program for factorial searching\n" +
                "}\n" +
                "program \n" +
                "\n" +
                "var int n, int factorial, int i\n" +
                "\n" +
                "begin\n" +
                "\tread(n);\n" +
                "\t\n" +
                "\tfactorial as 1;\n" +
                "\t\n" +
                "\tfor \n" +
                "\t\ti as 1 \n" +
                "\tto \n" +
                "\t\ti <= n\n" +
                "\tdo \n" +
                "begin\n" +
                "\t\tfactorial as factorial * i;\n" +
                "\t\ti as i + 1\n" +
                "end;\n" +
                "\twrite(factorial)\n" +
                "end.";
        Interpret.main(text);
    }

    @Test
    public void gameTest() throws IOException {
        //when user guess 100
        when(input.readLine()).thenReturn("2", "2", "1", "1", "2", "2", "1", "1", "0");

        String text = "{ \n" +
                "    Game guessing of number\n" +
                "\tif number of user more, then should enter 1, if less then 2, if equall then 0\n" +
                "}\n" +
                "program \n" +
                "\n" +
                "var int n, int answer, int rangeStart, int rangeEnd\n" +
                "\n" +
                "begin\n" +
                "\trangeStart as 0;\n" +
                "\trangeEnd as 500;\n" +
                "\tanswer as 1;\n" +
                "\t\n" +
                "\twhile answer <> 0\n" +
                "\tdo begin\n" +
                "\t\tn as rangeEnd / 2 - rangeStart / 2;\n" +
                "\t\tn as n + rangeStart;" +
                "\t\twrite(n);\n" +
                "\t\tread(answer);\n" +
                "\t\tif answer = 2 then rangeEnd as n\n" +
                "\t\telse if answer = 1 then rangeStart as n\n" +
                "\tend\n" +
                "end.\n";
        Interpret.main(text);
    }


}