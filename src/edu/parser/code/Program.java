package edu.parser.code;

import java.util.ArrayList;
import java.util.List;

import edu.interpret.Functions;
import edu.interpret.Globals;
import edu.parser.code.variables.Var;

public class Program {
    private List<Var> globalVariables;
    private Function main;
    private List<Function> functions;

    public Program() {
        globalVariables = new ArrayList<>();
        functions = new ArrayList<>();
    }

    public List<Var> getGlobalVariables() {
        return globalVariables;
    }

    public void setGlobalVariables(List<Var> globalVariables) {
        this.globalVariables = globalVariables;
    }

    public Function getMain() {
        return main;
    }

    public void setMain(Function main) {
        this.main = main;
    }

    public List<Function> getFunctions() {
        return functions;
    }

    public void setFunctions(List<Function> functions) {
        this.functions = functions;
    }

    public void addFunction(Function function) {
        functions.add(function);
    }
    public void process(){
        globalVariables.forEach(Globals::add);
        functions.forEach(Functions::add);

        functions.forEach(Function::process);
    }

}
