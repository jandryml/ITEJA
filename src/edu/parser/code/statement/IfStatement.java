package edu.parser.code.statement;

import edu.lexer.enums.Grammar;
import edu.parser.code.condition.Condition;

public class IfStatement extends Statement{
    private Condition condition;
    private Statement statement;

    public IfStatement(Grammar type) {
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
        if(condition.process()){
            statement.process();
        }
    }
}
