package edu.parser.code.statement;

import java.util.ArrayList;
import java.util.List;

import edu.parser.code.variables.Var;

public class ExecuteStatement extends Statement {
    private String identifier;
    private List<Var> params;

    public ExecuteStatement() {
        params = new ArrayList<>();
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public List<Var> getParams() {
        return params;
    }

    public void setParams(List<Var> params) {
        this.params = params;
    }

    public void addParam(Var var) {
        params.add(var);
    }

    @Override public void process() {

    }
}
