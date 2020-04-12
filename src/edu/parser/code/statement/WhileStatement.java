package edu.parser.code.statement;

import java.util.List;

import edu.interpret.Variables;
import edu.parser.code.condition.Condition;

public class WhileStatement extends Statement {
    private Condition condition;
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

    @Override public void process(Variables variables) {
        while (condition.process(variables)) {
            statements.forEach(statement -> statement.process(variables));
        }
    }
}
