package edu.parser.code.expression;

import java.util.ArrayList;
import java.util.List;

import edu.interpret.Variables;
import edu.interpret.exception.InterpretException;
import edu.parser.code.Pair;
import edu.parser.code.variables.Var;

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

    public int calculateTermValue(Variables variables){
        if (!factorList.isEmpty()) {
            int result = factorList.get(0).getValue().calculateFactorValue(variables);
            for (int i = 1; i < factorList.size(); i++) {
                Pair<Factor, String> actualFactor = factorList.get(i);
                switch (actualFactor.getOperator()) {
                case "*":
                    result = result * actualFactor.getValue().calculateFactorValue(variables);
                    break;
                case "/":
                    result = result / actualFactor.getValue().calculateFactorValue(variables);
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
