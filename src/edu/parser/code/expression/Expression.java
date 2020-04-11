package edu.parser.code.expression;

import java.util.ArrayList;
import java.util.List;

import edu.parser.code.Pair;

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


}

