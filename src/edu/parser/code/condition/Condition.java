package edu.parser.code.condition;

import java.util.ArrayList;
import java.util.List;

import edu.interpret.Variables;
import edu.interpret.exception.InterpretException;
import edu.lexer.enums.Grammar;
import edu.parser.code.Pair;

public class Condition {
    private List<Pair<LogicalExpression, String>> expressionList;

    public Condition() {
        expressionList = new ArrayList<>();
    }

    public List<Pair<LogicalExpression, String>> getExpressionList() {
        return expressionList;
    }

    public void setExpressionList(List<Pair<LogicalExpression, String>> expressionList) {
        this.expressionList = expressionList;
    }

    public void addLogicalExpression(LogicalExpression logicalExpression, String operator) {
        expressionList.add(new Pair<>(logicalExpression, operator));
    }

    public boolean process(Variables variables) {
        boolean value = expressionList.get(0).getValue().process(variables);

        for (int i = 1; i < expressionList.size(); i++) {
            if (expressionList.get(i).getOperator().equals(Grammar.AND.getValue())) {
                value = value & expressionList.get(i).getValue().process(variables);
            } else if (expressionList.get(i).getOperator().equals(Grammar.OR.getValue())) {
                value = value | expressionList.get(i).getValue().process(variables);
            } else {
                throw new InterpretException("Invalid operator: \"" + expressionList.get(i).getOperator() + "\" in condition!");
            }
        }

        return value;
    }
}
