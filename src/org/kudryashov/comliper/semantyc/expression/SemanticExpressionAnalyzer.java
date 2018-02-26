package org.kudryashov.comliper.semantyc.expression;

import org.kudryashov.comliper.Instances;
import org.kudryashov.comliper.elements.elementTypes.ProgramElement;
import org.kudryashov.comliper.elements.elementTypes.repositories.Repository;
import org.kudryashov.comliper.elements.elementTypes.Identifier;
import org.kudryashov.comliper.elements.elementTypes.NumberElement;
import org.kudryashov.comliper.elements.elementTypes.operator.BinaryOperator;
import org.kudryashov.comliper.elements.elementTypes.operator.Operator;
import org.kudryashov.comliper.elements.elementTypes.operator.UnaryOperator;
import org.kudryashov.comliper.elements.elementTypes.word.type.VariableType;
import org.kudryashov.comliper.lexical.LexicalElement;
import org.kudryashov.comliper.semantyc.exception.SemanticExpressionError;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.reverse;
import static org.kudryashov.comliper.elements.elementTypes.operator.Operator.Dimension.BINARY;
import static org.kudryashov.comliper.elements.elementTypes.operator.Operator.Dimension.UNARY;
import static org.kudryashov.comliper.elements.elementTypes.separator.Separator.Name.*;
import static org.kudryashov.comliper.elements.elementTypes.word.enumeration.Word.NOT;

public class SemanticExpressionAnalyzer {

    private Repository<Operator> operators = Instances.get(Instances.OPERATORS);

    private List<ProgramElement> elements = new ArrayList<>();
    private List<Operator> operations = fillOperations();
    private VariableType previousResult = null;

    public void put(ProgramElement element) {
        elements.add(element);
    }

    public VariableType analyze() {
        reverse(elements);
        if (elements.isEmpty()) {
            return previousResult;
        }
        while (elements.size() > 1) {
            analyzeOperators();
        }
        ProgramElement element = elements.get(0);
        elements.clear();
        previousResult = getOperandType(element);
        return previousResult;
    }

    private void analyzeOperators() {
        for (Operator operator : operations) {
            if (analyzeOperator(operator)) {
                break;
            }
        }
    }

    private boolean analyzeOperator(Operator operator) {
        if (operator.dimension() == UNARY) {
            return analyzeUnary((UnaryOperator) operator);
        } else if (operator.dimension() == BINARY) {
            return analyzeBinary((BinaryOperator) operator);
        } else {
            throw new IllegalArgumentException("unknown operator");
        }
    }

    private boolean analyzeBinary(BinaryOperator operator) {
        if (elements.contains(operator)) {
            int operationIndex = getOperatorIndex(operator);
            collapse(
                    operationIndex,
                    operator.resultType(
                            getOperandType(previousElement(operationIndex)),
                            getOperandType(nextElement(operationIndex))
                    )
            );
            return true;
        }
        return false;
    }

    private int getOperatorIndex(Operator operator) {
        return elements.indexOf(operator);
    }

    private boolean analyzeUnary(UnaryOperator operator) {
        if (elements.contains(operator)) {
            int index = getOperatorIndex(operator);
            VariableType type = getOperandType(previousElement(index - 1));
            VariableType resultType = operator.resultType(type);
            if (operator.operandTypes().contains(type)) {
                collapseUnary(index, resultType);
                return true;
            } else {
                throw new SemanticExpressionError("illegal unary operand " + type);
            }
        }
        return false;
    }

    private VariableType getOperandType(ProgramElement element) {
        if (element instanceof VariableType) {
            return (VariableType) element;
        } else if (element instanceof Identifier) {
            return ((Identifier) element).getVariableType();
        } else if (element instanceof NumberElement) {
            return ((NumberElement) element).getVariableType();
        }
        throw new SemanticExpressionError("cant calculate type of " + element);
    }

    private ProgramElement nextElement(int index) {
        return elements.get(index + 1);
    }

    private ProgramElement previousElement(int index) {
        return elements.get(index - 1);
    }

    private void collapse(int operationIndex, VariableType resultType) {
        elements.remove(operationIndex - 1);
        elements.remove(operationIndex - 1);
        elements.remove(operationIndex - 1);
        elements.add(operationIndex - 1, resultType);
    }

    private void collapseUnary(int index, VariableType resultType) {
        elements.remove(index);
        elements.remove(index);
        elements.add(index, resultType);
    }

    private List<Operator> fillOperations() {
        List<Operator> operations = new ArrayList<>();
        operations.add(operators.get(NOT));
        operations.add(operators.get(MULTIPLY));
        operations.add(operators.get(DIVIDE));
        operations.add(operators.get(LOGIC_AND));
        operations.add(operators.get(PLUS));
        operations.add(operators.get(MINUS));
        operations.add(operators.get(LOGIC_OR));
        operations.add(operators.get(LESS));
        operations.add(operators.get(LESS_OR_EQUALS));
        operations.add(operators.get(EQUAL));
        operations.add(operators.get(MORE));
        operations.add(operators.get(MORE_OR_EQUALS));
        return operations;
    }
}
