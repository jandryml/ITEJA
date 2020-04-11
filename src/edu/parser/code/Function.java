package edu.parser.code;

import java.util.ArrayList;
import java.util.List;

import edu.parser.code.statement.Statement;
import edu.parser.code.variables.Var;

public class Function {
    private String identifier;
    private List<Var> parameters;
    private List<Var> variables;
    private List<Statement> statements;

    public Function() {
        parameters = new ArrayList<>();
        variables = new ArrayList<>();
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

    public List<Var> getVariables() {
        return variables;
    }

    public void setVariables(List<Var> variables) {
        this.variables = variables;
    }

    public void addVariable(Var var){
        variables.add(var);
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

    public void process(){

    }

}
