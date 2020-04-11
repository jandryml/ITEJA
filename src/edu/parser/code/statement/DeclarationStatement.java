package edu.parser.code.statement;

import java.util.List;

import edu.interpret.InterpretHelper;
import edu.interpret.Variables;
import edu.parser.code.variables.Var;

public class DeclarationStatement extends Statement {

    private List<Var> variablesList;

    public List<Var> getVariablesList() {
        return variablesList;
    }

    public void setVariablesList(List<Var> variables) {
        this.variablesList = variables;
    }

    @Override public void process(Variables variables) {
        variablesList.forEach(var -> InterpretHelper.addVariable(var.getIdentifier(), var.getValue(), variables));
    }

}
