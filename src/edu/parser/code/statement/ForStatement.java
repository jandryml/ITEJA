package edu.parser.code.statement;

import java.util.List;

import edu.interpret.InterpretHelper;
import edu.interpret.Variables;
import edu.interpret.exception.InterpretException;
import edu.lexer.enums.Grammar;
import edu.lexer.enums.TokenType;
import edu.parser.code.condition.Condition;
import edu.parser.code.variables.Value;

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

    @Override public void process(Variables variables) {
        declare.process(variables);
        while (condition.process(variables)) {
            statements.forEach( statement -> statement.process(variables));
            processValue(variables);
        }
    }

    private void processValue(Variables variables) {
        Value value = InterpretHelper.getValue(identifier, variables);

        if (value.getType().equals(TokenType.NUMBER)) {
            if (processValue.equals(Grammar.INCREMENT.getValue())) {
                value.setExpressionValue(
                        InterpretHelper.getExpressionOf(value.getExpressionValue().calculateExpressionValue(variables) + 1));
            } else if (processValue.equals(Grammar.DECREMENT.getValue())) {
                value.setExpressionValue(
                        InterpretHelper.getExpressionOf(value.getExpressionValue().calculateExpressionValue(variables) - 1));
            } else {
                throw new InterpretException("Invalid operator\"" + processValue + "\" in FOR, must be \"++\", or \"--\"");
            }
        } else {
            throw new InterpretException("Variable \"" + identifier + "\" must be NUMBER");
        }
    }
}
