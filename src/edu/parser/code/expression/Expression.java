package edu.parser.code.expression;

import java.util.ArrayList;
import java.util.List;

import edu.interpret.exception.InterpretException;
import edu.interpret.global.InterpretHelper;

public class Expression {

    private List<Pair<Term, String>> termList;

    public Expression() {
        termList = new ArrayList<>();
    }

    public List<Pair<Term, String>> getExpressionList() {
        return termList;
    }

    public void setExpressionList(List<Pair<Term, String>> expressionList) {
        this.termList = expressionList;
    }

    public void addTerm(Term term, String operator) {
        termList.add(new Pair<>(term, operator));
    }

    public int process() {
        return calculateExpressionValue();
    }

    private int calculateExpressionValue() {
        if (!termList.isEmpty()) {
            int result = 0;
            for (Pair<Term, String> term : termList) {
                result += InterpretHelper.evaluateNumberSign(term.getValue().process(), term.getOperator());
            }
            return result;
        } else {
            throw new InterpretException("No terms present!");
        }
    }
}
