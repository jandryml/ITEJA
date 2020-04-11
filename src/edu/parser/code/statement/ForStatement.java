package edu.parser.code.statement;

import java.util.List;

import edu.parser.code.condition.Condition;

public class ForStatement extends Statement {
    private DeclarationStatement declare;
    private Condition condition;
    private String identifier;
    private String processValue;
    private List<Statement> statements;


    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public List<Statement> getStatements() {
        return statements;
    }

    public void setStatements(List<Statement> statements) {
        this.statements = statements;
    }

    public DeclarationStatement getDeclare() {
        return declare;
    }

    public void setDeclare(DeclarationStatement declare) {
        this.declare = declare;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getProcessValue() {
        return processValue;
    }

    public void setProcessValue(String processValue) {
        this.processValue = processValue;
    }

    @Override public void process() {

    }
}
