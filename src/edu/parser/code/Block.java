package edu.parser.code;

import java.util.ArrayList;
import java.util.List;

import edu.interpret.global.Constants;
import edu.interpret.global.Procedures;
import edu.interpret.global.Variables;
import edu.parser.code.statement.Statement;

public class Block {
    private List<Const> constList;
    private List<Var> varList;
    private List<Procedure> procedureList;
    private Statement statement;

    public Block() {
        constList = new ArrayList<>();
        varList = new ArrayList<>();
        procedureList = new ArrayList<>();
    }

    public List<Const> getConstList() {
        return constList;
    }

    public void setConstList(List<Const> constList) {
        this.constList = constList;
    }

    public List<Var> getVarList() {
        return varList;
    }

    public void setVarList(List<Var> varList) {
        this.varList = varList;
    }

    public List<Procedure> getProcedureList() {
        return procedureList;
    }

    public void setProcedureList(List<Procedure> procedureList) {
        this.procedureList = procedureList;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public void addProcedure(Procedure procedure) {
        procedureList.add(procedure);
    }

    public void process(){
        varList.forEach(Variables::add);
        constList.forEach(Constants::add);
        procedureList.forEach(Procedures::add);

        statement.process();
    }
}
