package org.kudryashov.comliper.syntax;

import org.kudryashov.comliper.Instances;
import org.kudryashov.comliper.elements.elementTypes.ProgramElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class ComplexAnalyzerTest {

    private SemanticAnalyzer sut;

    @BeforeMethod
    public void setup() {
        Instances.reset();
        sut = new SemanticAnalyzer();
    }

    @Test(dataProvider = "correctPrograms")
    public void shouldSuccess(String text) {
        sut.analyze(text);
    }

    @Test(dataProvider = "withError", expectedExceptions = RuntimeException.class)
    public void shouldFail(String text) {
        sut.analyze(text);
    }

    @DataProvider
    public static Object[][] withError() {
        return new Object[][] {
                {"program var int v1 begin write(v2) end."},
                {"program var int v1 begin v1 as true end."}
        };
    }

    @DataProvider
    public static Object[][] correctPrograms() {
        return new Object[][] {
                {"program var int v1, int v2 begin write(v1, v2 + v1) end."},
                {"program var int v1, int v2 begin v1 as 1; v2 as 2 end."}
        };
    }
}