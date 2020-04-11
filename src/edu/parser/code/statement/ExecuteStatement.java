package edu.parser.code.statement;

import java.util.ArrayList;
import java.util.List;

import edu.interpret.Functions;
import edu.interpret.InterpretHelper;
import edu.interpret.Variables;
import edu.interpret.exception.InterpretException;
import edu.lexer.enums.TokenType;
import edu.parser.code.expression.Expression;
import edu.parser.code.expression.Factor;
import edu.parser.code.variables.Value;
import edu.parser.code.variables.Var;

public class ExecuteStatement extends Statement {
    private String identifier;
    private List<Var> params;

    public ExecuteStatement() {
        params = new ArrayList<>();
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public List<Var> getParams() {
        return params;
    }

    public void setParams(List<Var> params) {
        this.params = params;
    }

    public void addParam(Var var) {
        params.add(var);
    }

    @Override public void process(Variables variables) {
        resolveParams(variables);
        Functions.getValue(identifier).process(params);
    }

    private void resolveParams(Variables variables) {
        List<Var> updatedParams = new ArrayList<>();
        for (Var var : params) {
            if (var.getValue().getType().equals(TokenType.STRING)) {
                updatedParams.add(var);
            } else if (var.getValue().getType().equals(TokenType.NUMBER)) {
                try {
                    Expression expression = var.getValue().getExpressionValue();
                    if (expression.getExpressionList().size() == 1
                            && expression.getExpressionList().get(0).getValue().getFactorList().size() == 1) {
                        Factor factor = expression.getExpressionList().get(0).getValue().getFactorList().get(0).getValue();
                        String identifier = factor.getIdentifier();
                        if (identifier != null) {
                            Value value = InterpretHelper.getValue(identifier, variables);
                            if (value.getType().equals(TokenType.STRING)) {
                                updatedParams.add(new Var("param", value));
                                continue;
                            }
                        }
                    }
                    updatedParams.add(var);
                } catch (InterpretException e) {
                    throw new InterpretException("Invalid params in function call: \"" + identifier);
                }
            } else {
                throw new InterpretException("Unknown data type!");
            }
        }
        params = updatedParams;
    }
}
