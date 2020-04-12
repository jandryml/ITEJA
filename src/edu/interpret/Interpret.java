package edu.interpret;

import edu.parser.code.Program;

public class Interpret {
    private Program program;

    public Interpret(Program program) {
        this.program = program;

    }

    public void process() {
        InterpretHelper.setPrintStream(System.out);
        InterpretHelper.setInputStream(System.in);
        program.process();
    }
}
