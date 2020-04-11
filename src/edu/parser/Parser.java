package edu.parser;

import java.util.ArrayList;
import java.util.List;

import edu.lexer.Token;
import edu.lexer.enums.Grammar;
import edu.lexer.enums.TokenType;
import edu.parser.code.Function;
import edu.parser.code.Pair;
import edu.parser.code.Program;
import edu.parser.code.condition.Condition;
import edu.parser.code.condition.LogicalExpression;
import edu.parser.code.expression.Expression;
import edu.parser.code.expression.Factor;
import edu.parser.code.expression.Term;
import edu.parser.code.statement.*;
import edu.parser.code.variables.Value;
import edu.parser.code.variables.Var;
import edu.parser.exception.ParserException;

public class Parser {
    private ParserHelper parserHelper;

    public Parser(ParserHelper parserHelper) {
        this.parserHelper = parserHelper;
    }

    public Program buildAst() {
        return readProgram();
    }

    private Program readProgram() {
        Program program = new Program();
        program.setGlobalVariables(readGlobals());
        program.setMain(readMain());
        while (!isPeekEqualOneOf(Grammar.TERMINATE)) {
            program.addFunction(readFunction());
        }
        return program;
    }

    private Function readFunction() {
        Function function = new Function();
        expectSymbol(TokenType.KEYWORD, Grammar.FUNCTION);
        function.setIdentifier(getIdentifier());

        function.setParameters(readFuncParams());

        function.setStatements(readCodeBlock());

        return function;
    }

    private List<Var> readFuncParams() {
        List<Var> vars = new ArrayList<>();
        expectSymbol(TokenType.SEPARATOR, Grammar.SQUARE_BRACKET_LEFT);

        boolean run = !isPeekEqualOneOf(Grammar.SQUARE_BRACKET_RIGHT);
        while (run) {
            Var var;
            if (isPeekEqualOneOf(Grammar.STRING)) {
                expectSymbol(TokenType.KEYWORD, Grammar.STRING);
                var = new Var(getIdentifier(), new Value(TokenType.STRING));
            } else if (isPeekEqualOneOf(Grammar.NUMBER)) {
                expectSymbol(TokenType.KEYWORD, Grammar.NUMBER);
                var = new Var(getIdentifier(), new Value(TokenType.NUMBER));
            } else {
                throw new ParserException("Unexpected function param datatype: " + parserHelper.pop().getValue());
            }
            vars.add(var);
            if (parserHelper.peekType().equals(TokenType.SEPARATOR)) {
                if (parserHelper.peekValue().equals(Grammar.SQUARE_BRACKET_RIGHT.getValue())) {
                    parserHelper.pop();
                    run = false;
                } else {
                    expectSymbol(TokenType.SEPARATOR, Grammar.COMMA);
                }
            } else {
                throw new ParserException("Separator expected!");
            }
        }

        return vars;
    }

    private Function readMain() {
        Function main = new Function();
        expectSymbol(TokenType.KEYWORD, Grammar.MAIN);

        main.setStatements(readCodeBlock());
        return main;
    }

    private List<Statement> readCodeBlock() {
        expectSymbol(TokenType.SEPARATOR, Grammar.CURLY_BRACKET_LEFT);
        List<Statement> statements = new ArrayList<>();
        while (!isPeekEqualOneOf(Grammar.CURLY_BRACKET_RIGHT)) {

            if (parserHelper.isType(TokenType.KEYWORD)) {
                switch (Grammar.getType(parserHelper.peekValue().toLowerCase())) {
                case NUMBER:

                case STRING: {
                    statements.add(readDeclaration());
                    break;
                }
                case EXECUTE: {
                    statements.add(readExecuteStatement());
                    break;
                }
                case IF: {
                    statements.add(readIfStatement());
                    break;
                }
                case WHILE: {
                    statements.add(readWhileStatement());
                    break;
                }
                case FOR: {
                    statements.add(readForStatement());
                    break;
                }
                case READ: {
                    statements.add(readReadStatement());
                    break;
                }
                case WRITE: {
                    statements.add(readWriteStatement());
                    break;
                }
                default:
                    throw new ParserException("Non valid word: \"" + parserHelper.pop() + "\"");
                }

            } else if (parserHelper.isType(TokenType.IDENTIFIER)) {
                statements.add(readAssigmentStatement());
            } else {
                throw new ParserException("Non valid word: \"" + parserHelper.pop().getValue() + "\"");
            }
        }
        expectSymbol(TokenType.SEPARATOR, Grammar.CURLY_BRACKET_RIGHT);
        return statements;
    }

    private Statement readExecuteStatement() {
        ExecuteStatement statement = new ExecuteStatement();
        expectSymbol(TokenType.KEYWORD, Grammar.EXECUTE);
        statement.setIdentifier(getIdentifier());

        expectSymbol(TokenType.SEPARATOR, Grammar.SQUARE_BRACKET_LEFT);

        boolean run = !isPeekEqualOneOf(Grammar.SQUARE_BRACKET_RIGHT);
        while (run) {
            readParam(statement);
            if (parserHelper.peekType().equals(TokenType.SEPARATOR)) {
                if (parserHelper.peekValue().equals(Grammar.SQUARE_BRACKET_RIGHT.getValue())) {
                    parserHelper.pop();
                    run = false;
                } else {
                    expectSymbol(TokenType.SEPARATOR, Grammar.COMMA);
                }
            } else {
                throw new ParserException("Separator expected!");
            }
        }
        expectSymbol(TokenType.SEPARATOR, Grammar.SEMICOLON);

        return statement;
    }

    private void readParam(ExecuteStatement statement) {
        Var var = getVar();
        statement.addParam(var);
    }

    private Statement readIfStatement() {
        IfStatement statement = new IfStatement();
        expectSymbol(TokenType.KEYWORD, Grammar.IF);

        statement.setCondition(readCondition());
        statement.setStatements(readCodeBlock());
        return statement;
    }

    private Statement readWhileStatement() {
        WhileStatement statement = new WhileStatement();
        expectSymbol(TokenType.KEYWORD, Grammar.WHILE);

        statement.setCondition(readCondition());
        statement.setStatements(readCodeBlock());
        return statement;
    }

    private Statement readForStatement() {
        ForStatement statement = new ForStatement();
        expectSymbol(TokenType.KEYWORD, Grammar.FOR);
        expectSymbol(TokenType.SEPARATOR, Grammar.SQUARE_BRACKET_LEFT);

        statement.setDeclare(readDeclaration());

        statement.setCondition(readCondition());
        expectSymbol(TokenType.SEPARATOR, Grammar.SEMICOLON);

        statement.setIdentifier(getIdentifier());
        statement.setProcessValue(parserHelper.pop().getValue());
        expectSymbol(TokenType.SEPARATOR, Grammar.SQUARE_BRACKET_RIGHT);

        statement.setStatements(readCodeBlock());
        return statement;
    }

    private Statement readReadStatement() {
        expectSymbol(TokenType.KEYWORD, Grammar.READ);
        ReadStatement statement = new ReadStatement();

        parserHelper.pop();
        statement.setIdentifier(getIdentifier());
        expectSymbol(TokenType.SEPARATOR, Grammar.SEMICOLON);

        return statement;
    }

    private Statement readWriteStatement() {
        expectSymbol(TokenType.KEYWORD, Grammar.WRITE);
        WriteStatement statement = new WriteStatement();

        Var var = getVar();

        statement.setVar(var);
        expectSymbol(TokenType.SEPARATOR, Grammar.SEMICOLON);
        return statement;
    }

    private Var getVar() {
        Var var;
        if (parserHelper.peekType().equals(TokenType.STRING)) {
            Value value = new Value(TokenType.STRING);
            value.setStringValue(parserHelper.pop().getValue());
            var = new Var("param", value);
        } else {
            Value value = new Value(TokenType.NUMBER);
            value.setExpressionValue(readExpression());
            var = new Var("param", value);
        }
        return var;
    }

    private Statement readAssigmentStatement() {
        AssigmentStatement statement = new AssigmentStatement();
        statement.setIdentifier(getIdentifier());
        expectSymbol(TokenType.KEYWORD, Grammar.ASSIGMENT);

        if (isPeekEqualOneOf(Grammar.STRING)) {
            Value value = new Value(TokenType.STRING);
            value.setStringValue(parserHelper.pop().getValue());
            statement.setValue(value);
        } else {
            Value value = new Value(TokenType.NUMBER);
            value.setExpressionValue(readExpression());
            statement.setValue(value);
        }

        expectSymbol(TokenType.SEPARATOR, Grammar.SEMICOLON);
        return statement;
    }

    private Condition readCondition() {
        Condition condition = new Condition();
        expectSymbol(TokenType.SEPARATOR, Grammar.SQUARE_BRACKET_LEFT);
        condition.addLogicalExpression(readLogicalExpression(), null);

        while (!isPeekEqualOneOf(Grammar.SQUARE_BRACKET_RIGHT)) {
            String operator = parserHelper.pop().getValue();
            condition.addLogicalExpression(readLogicalExpression(), operator);
        }
        expectSymbol(TokenType.SEPARATOR, Grammar.SQUARE_BRACKET_RIGHT);
        return condition;
    }

    private LogicalExpression readLogicalExpression() {
        LogicalExpression logicalExpression = new LogicalExpression();
        logicalExpression.setFirstPart(readExpression());

        logicalExpression.setOperator(parserHelper.pop().getValue());
        logicalExpression.setSecondPart(readExpression());
        //   logicalExpression.

        return logicalExpression;
    }

    private DeclarationStatement readDeclaration() {
        DeclarationStatement statement = new DeclarationStatement();
        statement.setVariables(readVars());
        return statement;
    }

    private List<Var> readGlobals() {
        expectSymbol(TokenType.KEYWORD, Grammar.GLOBALS);
        List<Var> globals = readVars();
        expectSymbol(TokenType.KEYWORD, Grammar.END);
        return globals;
    }

    private List<Var> readVars() {
        List<Var> varList = new ArrayList<>();

        boolean run = true;
        while (run) {
            Var var;
            switch (parserHelper.pop().getValue()) {
            case "string": {
                var = readString();
                break;
            }
            case "number": {
                var = readNumber();
                break;
            }
            default: {
                throw new ParserException("Invalid data type!");
            }
            }

            if (parserHelper.isType(TokenType.SEPARATOR)) {
                if (isPeekEqualOneOf(Grammar.SEMICOLON)) {
                    run = false;
                }
                parserHelper.pop();
            } else {
                throw new ParserException("Separator was expected!");
            }
            varList.add(var);
        }
        return varList;
    }

    private Var readNumber() {
        String identifier = getIdentifier();
        Value value = new Value(TokenType.NUMBER);
        if (isPeekEqualOneOf(Grammar.ASSIGMENT)) {
            expectSymbol(TokenType.KEYWORD, Grammar.ASSIGMENT);
            value.setExpressionValue(readExpression());
        } else {
            value.setExpressionValue(null);
        }

        return new Var(identifier, value);
    }

    private Var readString() {
        String identifier = getIdentifier();
        Value value = new Value(TokenType.STRING);

        if (isPeekEqualOneOf(Grammar.ASSIGMENT)) {
            expectSymbol(TokenType.KEYWORD, Grammar.ASSIGMENT);
            value.setStringValue(getString());
        } else {
            value.setStringValue("");

        }

        return new Var(identifier, value);
    }

    private Expression readExpression() {
        Expression expression = new Expression();

        String prefix = isPeekEqualOneOf(Grammar.PLUS, Grammar.MINUS) ? parserHelper.pop().getValue() : null;

        expression.addTerm(readTerm(), prefix);

        while (isPeekEqualOneOf(Grammar.PLUS, Grammar.MINUS)) {
            String operator = parserHelper.pop().getValue();
            expression.addTerm(readTerm(), operator);
        }

        return expression;
    }

    private Term readTerm() {
        Term term = new Term();

        term.addFactor(readFactor(), null);

        while (isPeekEqualOneOf(Grammar.DIVISION, Grammar.MULTIPLY)) {
            String operator = parserHelper.pop().getValue();
            term.addFactor(readFactor(), operator);
        }
        return term;
    }

    private Factor readFactor() {
        Factor factor = new Factor();

        if (parserHelper.peekType().equals(TokenType.IDENTIFIER)) {
            factor.setIdentifier(getIdentifier());
        } else if (parserHelper.peekType().equals(TokenType.NUMBER)) {
            factor.setLiteral(getNumber());
        } else {
            expectSymbol(TokenType.OPERATOR, Grammar.ROUND_BRACKET_LEFT);
            factor.setExpression(readExpression());
            expectSymbol(TokenType.OPERATOR, Grammar.ROUND_BRACKET_RIGHT);

        }

        return factor;
    }

    private String getIdentifier() {
        if (parserHelper.isType(TokenType.IDENTIFIER)) {
            return parserHelper.pop().getValue();
        } else {
            throw new ParserException("Identifier was expected!");
        }
    }

    private String getString() {
        if (parserHelper.peekType().equals(TokenType.STRING)) {
            return parserHelper.pop().getValue();
        } else {
            throw new ParserException("String was expected!");
        }
    }

    private int getNumber() {
        if (parserHelper.peekType().equals(TokenType.NUMBER)) {
            return Integer.parseInt(parserHelper.pop().getValue());
        } else {
            throw new ParserException("Number was expected!");
        }
    }

    private boolean isPeekEqualOneOf(Grammar... grammars) {
        for (Grammar value : grammars) {
            if (parserHelper.peekValue().toLowerCase().equals(value.getValue())) {
                return true;
            }
        }
        return false;
    }

    private void expectSymbol(TokenType tokenType, Grammar word) {
        if (parserHelper.isSame(tokenType, word)) {
            parserHelper.pop();
        } else {
            throw new ParserException("\"" + word.getValue() + "\" was expected!");
        }
    }
}
