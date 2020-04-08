package edu.parser.code.statement;

import edu.lexer.enums.Grammar;
import edu.parser.code.condition.Condition;

public class WhileStatement extends Statement {
    private Condition condition;
    private Statement statement;

    public WhileStatement(Grammar type) {
        super(type);
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    @Override public void process() {
        while(condition.process()){
            statement.process();
        }
    }
}
