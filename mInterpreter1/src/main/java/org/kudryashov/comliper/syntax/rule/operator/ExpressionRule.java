package org.kudryashov.comliper.syntax.rule.operator;

import org.kudryashov.comliper.elements.elementTypes.operator.BinaryOperator;
import org.kudryashov.comliper.elements.elementTypes.util.Expression;
import org.kudryashov.comliper.elements.elementTypes.util.Operand;
import org.kudryashov.comliper.elements.elementTypes.word.type.VariableType;
import org.kudryashov.comliper.syntax.Context;
import org.kudryashov.comliper.syntax.rule.OperandRule;
import org.kudryashov.comliper.syntax.rule.SyntaxRule;
import org.kudryashov.comliper.syntax.rule.operation.relation.RelationOperationRule;

public class ExpressionRule implements SyntaxRule<Expression> {

    private Operand operand1;
    private BinaryOperator operator;
    private Operand operand2;

    @Override
    public Expression apply(Context lexemes) {
        if (isExpression(lexemes, new OperandRule())) {
            return new Expression(operand1, operator, operand2);
        }
        return null;
    }

    private boolean isExpression(Context lexemes, OperandRule operandRule) {
        operand1 = operandRule.apply(lexemes);
        if (operand1 != null) {
            operator = new RelationOperationRule().applyWithReset(lexemes);
            if (operator != null) {
                operand2 = operandRule.applyWithReset(lexemes);
            }
            return true;
        }
        return false;
    }
}
