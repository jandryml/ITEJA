package edu.parser.code;

import java.util.ArrayList;
import java.util.List;

import edu.interpret.Variables;
import edu.interpret.exception.InterpretException;
import edu.parser.code.statement.Statement;
import edu.parser.code.variables.Var;

public class Function {
    private String identifier;
    private List<Var> parameters;
    private Variables runtimeVariables;
    private List<Statement> statements;

    public Function() {
        parameters = new ArrayList<>();
        statements = new ArrayList<>();
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public List<Var> getParameters() {
        return parameters;
    }

    public void setParameters(List<Var> parameters) {
        this.parameters = parameters;
    }

    public void addParameter(Var var){
        parameters.add(var);
    }

    public Variables getVariables() {
        return runtimeVariables;
    }

    public void setVariables(Variables variables) {
        this.runtimeVariables = variables;
    }

    public void addVariable(Var var){
        runtimeVariables.add(var);
    }

    public List<Statement> getStatements() {
        return statements;
    }

    public void setStatements(List<Statement> statements) {
        this.statements = statements;
    }

    public void addStatement(Statement statement){
        statements.add(statement);
    }

    public void process(List<Var> parameters){
        runtimeVariables = new Variables();
        if(parameters != null && !parameters.isEmpty()){
            processParams(parameters);
        }
        statements.forEach(statement -> statement.process(runtimeVariables));
    }

    private void processParams(List<Var> callParameters) {
        if(parameters.size() == callParameters.size()){
            for (int i = 0; i < parameters.size(); i++) {
                if(parameters.get(i).getValue().getType().equals(callParameters.get(i).getValue().getType())){
                    parameters.get(i).setValue(callParameters.get(i).getValue());
                } else{
                    throw new InterpretException("Invalid data types of params in call of function: " + identifier);
                }
            }
            runtimeVariables.setVariables(parameters);
        } else{
            throw new InterpretException("Invalid count of params in call of function: " + identifier);
        }
    }

}
