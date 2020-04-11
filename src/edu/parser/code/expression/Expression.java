package edu.parser.code.expression;

import java.util.ArrayList;
import java.util.List;

import edu.interpret.InterpretHelper;
import edu.interpret.Variables;
import edu.interpret.exception.InterpretException;
import edu.parser.code.Pair;
import edu.parser.code.variables.Var;

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

    public void process(){

    }

    public int calculateExpressionValue(Variables variables) {
        if (!termList.isEmpty()) {
            int result = 0;
            for (Pair<Term, String> term : termList) {
                result += InterpretHelper.evaluateNumberSign(term.getValue().calculateTermValue(variables), term.getOperator());
            }
            return result;
        } else {
            throw new InterpretException("No terms present!");
        }
    }
}

