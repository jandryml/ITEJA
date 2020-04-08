package edu.parser.code.statement;

import java.util.ArrayList;
import java.util.List;

import edu.lexer.enums.Grammar;

public class BeginEndStatement extends Statement {
    private List<Statement> statementList;

    public BeginEndStatement(Grammar type) {
        super(type);
        statementList = new ArrayList<>();
    }

    public List<Statement> getStatementList() {
        return statementList;
    }

    public void setStatementList(List<Statement> statementList) {
        this.statementList = statementList;
    }

    public void addStatement(Statement statement) {
        statementList.add(statement);
    }

    @Override public void process() {
        statementList.forEach(Statement::process);
    }
}
