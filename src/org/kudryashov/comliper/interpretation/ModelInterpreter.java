package org.kudryashov.comliper.interpretation;

import org.kudryashov.comliper.elements.elementTypes.ElementName;
import org.kudryashov.comliper.elements.elementTypes.Identifier;
import org.kudryashov.comliper.elements.elementTypes.NumberElement;
import org.kudryashov.comliper.elements.elementTypes.repositories.Repository;
import org.kudryashov.comliper.elements.elementTypes.separator.Separator;
import org.kudryashov.comliper.elements.elementTypes.util.poliz.AdditionalPolizElements;
import org.kudryashov.comliper.elements.elementTypes.util.poliz.PolizElementNumber;
import org.kudryashov.comliper.elements.elementTypes.word.enumeration.Word;
import org.kudryashov.comliper.elements.elementTypes.word.type.VariableTypes;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import static org.kudryashov.comliper.elements.elementTypes.separator.Separator.Name.*;
import static org.kudryashov.comliper.elements.elementTypes.util.poliz.AdditionalPolizElements.GOTO;
import static org.kudryashov.comliper.elements.elementTypes.util.poliz.AdditionalPolizElements.REVERT_IF;
import static org.kudryashov.comliper.elements.elementTypes.word.enumeration.Word.*;

/*I'm so sorry for this class*/
public class ModelInterpreter implements Interpreter {

    private Deque<ElementName> operandStack = new ArrayDeque<>();
    private OutputStream output;

    public ModelInterpreter(OutputStream output) {
        this.output = output;
    }

    @Override
    public void interpret(List<ElementName> poliz, Repository<Identifier> identifiers) {
        for (int i = 0; i < poliz.size(); ++i) {
            ElementName element = poliz.get(i);
            if (shouldPasteToOperandStack(element)) {
                operandStack.push(element);
            } else if (element instanceof Separator.Name) {
                processSeparator(identifiers, element);
            } else if (element instanceof Word) {
                processWord(identifiers, element);
            } else if (element instanceof AdditionalPolizElements) {
                i = processGotoOperators(i, element);
            }
        }
    }

    private int processGotoOperators(int i, ElementName element) {
        if (element.equals(REVERT_IF)) {
            if (operandStack.pollLast().equals(FALSE)) {
                i = ((PolizElementNumber) operandStack.pollLast()).number - 1;
            } else {
                operandStack.pollLast();
            }
        } else if (element.equals(GOTO)) {
            i = ((PolizElementNumber) operandStack.poll()).number - 1;
        }
        return i;
    }

    private void processWord(Repository<Identifier> identifiers, ElementName element) {
        if (element.equals(AS)) {
            processAs(identifiers);
        } else if (element.equals(WRITE)) {
            processWrite(identifiers);
        }
    }

    private void processSeparator(Repository<Identifier> identifiers, ElementName element) {
        if (element.equals(PLUS)) {
            operandStack.push(new NumberElement.Value(getPlusResult(identifiers)));
        } else if (element.equals(MINUS)) {
            operandStack.push(new NumberElement.Value(getMinusResult(identifiers)));
        } else if (element.equals(LESS)) {
            processLess(identifiers);
        } else if (element.equals(MORE)) {
            processMore(identifiers);
        } else if (element.equals(MORE_OR_EQUALS)) {
            Float v1 = Float.valueOf(pollOperandValue(identifiers));
            Float v2 = Float.valueOf(pollOperandValue(identifiers));
            operandStack.push(v1 >= v2 ? TRUE : FALSE);
        } else if (element.equals(LESS_OR_EQUALS)) {
            Float v1 = Float.valueOf(pollOperandValue(identifiers));
            Float v2 = Float.valueOf(pollOperandValue(identifiers));
            operandStack.push(v1 <= v2 ? TRUE : FALSE);
        } else if (element.equals(NOT_EQUALS)) {
            Float v1 = Float.valueOf(pollOperandValue(identifiers));
            Float v2 = Float.valueOf(pollOperandValue(identifiers));
            operandStack.push(v1.equals(v2) ? FALSE : TRUE);
        } else if (element.equals(EQUAL)) {
            Float v1 = Float.valueOf(pollOperandValue(identifiers));
            Float v2 = Float.valueOf(pollOperandValue(identifiers));
            operandStack.push(v1.equals(v2) ? TRUE : FALSE);
        } else if (element.equals(DIVIDE)) {
            operandStack.push(divide(identifiers));
        } else if (element.equals(MULTIPLY)) {
            operandStack.push(multiply(identifiers));
        }
    }

    private NumberElement.Value multiply(Repository<Identifier> identifiers) {
        NumberElement el1 = pollNumberOperand(operandStack.pollLast(), identifiers);
        NumberElement el2 = pollNumberOperand(operandStack.pollLast(), identifiers);
        if (el1.type().equals(VariableTypes.INT) && el2.type().equals(VariableTypes.INT)) {
            return new NumberElement.Value(el1.value().intValue() * el2.value().intValue());
        } else if (el1.type().equals(VariableTypes.FLOAT) || el2.type().equals(VariableTypes.FLOAT)) {
            return new NumberElement.Value(el1.value().floatValue() * el2.value().floatValue());
        }
        throw new IllegalArgumentException();
    }

    private NumberElement.Value divide(Repository<Identifier> identifiers) {
        NumberElement el1 = pollNumberOperand(operandStack.pollLast(), identifiers);
        NumberElement el2 = pollNumberOperand(operandStack.pollLast(), identifiers);
        if (el1.type().equals(VariableTypes.INT) && el2.type().equals(VariableTypes.INT)) {
            return new NumberElement.Value(el1.value().intValue() / el2.value().intValue());
        } else if (el1.type().equals(VariableTypes.FLOAT) || el2.type().equals(VariableTypes.FLOAT)) {
            return new NumberElement.Value(el1.value().floatValue() / el2.value().floatValue());
        }
        throw new IllegalArgumentException();
    }

    private void processMore(Repository<Identifier> identifiers) {
        Float v1 = Float.valueOf(pollOperandValue(identifiers));
        Float v2 = Float.valueOf(pollOperandValue(identifiers));
        operandStack.push(v1 > v2 ? TRUE : FALSE);
    }

    private void processLess(Repository<Identifier> identifiers) {
        Float v1 = Float.valueOf(pollOperandValue(identifiers));
        Float v2 = Float.valueOf(pollOperandValue(identifiers));
        operandStack.push(v1 < v2 ? TRUE : FALSE);
    }

    private boolean shouldPasteToOperandStack(ElementName element) {
        return element instanceof NumberElement.Value
                || element instanceof Identifier.Name
                || element instanceof PolizElementNumber
                || element.equals(TRUE)
                || element.equals(FALSE);
    }

    private void processAs(Repository<Identifier> identifiers) {
        Identifier.Name identifierName = (Identifier.Name) operandStack.pollLast();
        Identifier identifier = identifiers.get(identifierName);
        identifier.setValue(operandStack.pollLast());
    }

    private void processWrite(Repository<Identifier> identifiers) {
        try {
            output.write(pollOperandValue(identifiers).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String pollOperandValue(Repository<Identifier> identifiers) {
        ElementName elementName = operandStack.pollLast();
        String value;
        if (elementName instanceof Identifier.Name) {
            value = identifiers.get(elementName).getValue().toString();
        } else {
            value = elementName.toString();
        }
        return value;
    }

    private NumberElement pollNumberOperand(ElementName name, Repository<Identifier> identifiers) {
        if (name instanceof NumberElement.Value) {
            return new NumberElement((NumberElement.Value) name);
        } else if (name instanceof Identifier.Name) {
            Identifier identifier = identifiers.get(name);
            if (identifier.type().equals(VariableTypes.INT)) {
                return new NumberElement(new NumberElement.Value(Integer.parseInt(identifier.getValue().toString())));
            }else if (identifier.type().equals(VariableTypes.FLOAT)) {
                return new NumberElement(new NumberElement.Value(Float.parseFloat(identifier.getValue().toString())));
            } else {
                throw new IllegalArgumentException();
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    private Number getPlusResult(Repository<Identifier> identifiers) {
        NumberElement el1 = pollNumberOperand(operandStack.pollLast(), identifiers);
        NumberElement el2 = pollNumberOperand(operandStack.pollLast(), identifiers);
        if (el1.type().equals(VariableTypes.INT) && el2.type().equals(VariableTypes.INT)) {
            return el1.value().intValue() + el2.value().intValue();
        } else if (el1.type().equals(VariableTypes.FLOAT) || el2.type().equals(VariableTypes.FLOAT)) {
            return el1.value().floatValue() + el2.value().floatValue();
        }
        throw new IllegalArgumentException();
    }

    private Number getMinusResult(Repository<Identifier> identifiers) {
        NumberElement el1 = pollNumberOperand(operandStack.pollLast(), identifiers);
        NumberElement el2 = pollNumberOperand(operandStack.pollLast(), identifiers);
        if (el1.type().equals(VariableTypes.INT) && el2.type().equals(VariableTypes.INT)) {
            return (Integer) el1.value() - (Integer) el2.value();
        } else if (el1.type().equals(VariableTypes.FLOAT) || el2.type().equals(VariableTypes.FLOAT)) {
            return (Float) el1.value() - (Float) el2.value();
        }
        throw new IllegalArgumentException();
    }
}
