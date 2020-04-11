package edu.parser.code.statement;

import java.util.List;

import edu.parser.code.variables.Var;

public class DeclarationStatement extends Statement {

    private List<Var> variables;

    public List<Var> getVariables() {
        return variables;
    }

    public void setVariables(List<Var> variables) {
        this.variables = variables;
    }

    @Override public void process() {

    }
}
