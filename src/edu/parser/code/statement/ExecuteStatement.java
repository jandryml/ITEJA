package edu.parser.code.statement;

import java.util.ArrayList;
import java.util.List;

import edu.interpret.Functions;
import edu.interpret.InterpretHelper;
import edu.interpret.Variables;
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

    @Override public void process(Variables variables) {
        Functions.getValue(identifier).process(resolveParams(variables), variables);
    }

    private List<Var> resolveParams(Variables variables) {
        List<Var> updatedParams = new ArrayList<>();
        for (Var var : params) {
            var = InterpretHelper.transferVariable(var,variables);
            updatedParams.add(var);
        }
        return updatedParams;
    }
}
