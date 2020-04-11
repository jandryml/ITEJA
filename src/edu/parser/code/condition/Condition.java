package edu.parser.code.condition;

import java.util.ArrayList;
import java.util.List;

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
}
