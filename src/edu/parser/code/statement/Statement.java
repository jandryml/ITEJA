package edu.parser.code.statement;


import edu.interpret.Variables;

public abstract class Statement {

    public Statement() {
    }

    public abstract void process(Variables variables);
}
