package edu.parser.code.expression;

import java.util.ArrayList;
import java.util.List;

import edu.parser.code.Pair;

public class Term {
    private List<Pair<Factor, String>> factorList;

    public Term() {
        factorList = new ArrayList<>();
    }

    public List<Pair<Factor, String>> getFactorList() {
        return factorList;
    }

    public void setFactorList(List<Pair<Factor, String>> termList) {
        this.factorList = termList;
    }

    public void addFactor(Factor factor, String operator) {
        factorList.add(new Pair<>(factor, operator));
    }
}
