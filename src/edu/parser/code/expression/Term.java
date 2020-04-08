package edu.parser.code.expression;

import java.util.ArrayList;
import java.util.List;

import edu.interpret.exception.InterpretException;

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

    public int process() {
        if (!factorList.isEmpty()) {
            int result = factorList.get(0).getValue().process();
            for (int i = 1; i < factorList.size(); i++) {
                Pair<Factor, String> actualFactor = factorList.get(i);
                switch (actualFactor.getOperator()) {
                case "*":
                    result = result * actualFactor.getValue().process();
                    break;
                case "/":
                    result = result / actualFactor.getValue().process();
                    break;
                default:
                    throw new InterpretException("Unknown operator: \"" + actualFactor.getOperator());

                }
            }
            return result;
        } else {
            throw new InterpretException("No factors present!");
        }
    }
}
