//package edu.parser;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import edu.lexer.LexerHelper;
//import edu.lexer.enums.Grammar;
//import edu.lexer.enums.TokenType;
//import edu.parser.code.*;
//import edu.parser.code.condition.BasicCondition;
//import edu.parser.code.condition.Condition;
//import edu.parser.code.condition.OddCondition;
//import edu.parser.code.exception.ParserException;
//import edu.parser.code.expression.Expression;
//import edu.parser.code.expression.Factor;
//import edu.parser.code.expression.Term;
//import edu.parser.code.statement.*;
//
//public class Parser {
//    private ParserHelper parserHelper;
//
//    public Parser(ParserHelper parserHelper) {
//        this.parserHelper = parserHelper;
//    }
//
//    public Program buildAst() {
//        return readProgram();
//    }
//
//    private Program readProgram() {
//        Block block = readBlock();
//        expectSymbol(TokenType.SEPARATOR, Grammar.DOT);
//        return new Program(block);
//    }
//
//    private Block readBlock() {
//        Block block = new Block();
//
//        if (parserHelper.isSame(TokenType.KEYWORD, Grammar.CONST)) {
//            block.setConstList(readConstants());
//        }
//
//        if (parserHelper.isSame(TokenType.KEYWORD, Grammar.VAR)) {
//            block.setVarList(readVars());
//        }
//
//        while (parserHelper.isSame(TokenType.KEYWORD, Grammar.PROCEDURE)) {
//            block.addProcedure(readProcedure());
//        }
//
//        block.setStatement(readStatement());
//
//        return block;
//    }
//
//    private List<Const> readConstants() {
//        List<Const> constList = new ArrayList<>();
//
//        expectSymbol(TokenType.KEYWORD, Grammar.CONST);
//
//        boolean run = true;
//        while (run) {
//            Const aConst = new Const();
//
//            aConst.setIdentifier(getIdentifier());
//
//            expectSymbol(TokenType.IDENTIFIER, Grammar.EQUALS);
//
//            aConst.setValue(getLiteral());
//
//            if (parserHelper.isType(TokenType.SEPARATOR)) {
//                if (isPeekEqual(Grammar.SEMICOLON.getValue())) {
//                    run = false;
//                }
//                parserHelper.pop();
//            } else {
//                throw new ParserException("Separator was expected!");
//            }
//            constList.add(aConst);
//        }
//        return constList;
//    }
//
//    private List<Var> readVars() {
//        List<Var> varList = new ArrayList<>();
//        expectSymbol(TokenType.KEYWORD, Grammar.VAR);
//
//        boolean run = true;
//        while (run) {
//            Var var = new Var();
//            var.setIdentifier(getIdentifier());
//
//            if (parserHelper.isType(TokenType.SEPARATOR)) {
//                if (isPeekEqual(Grammar.SEMICOLON.getValue())) {
//                    run = false;
//                }
//                parserHelper.pop();
//            } else {
//                throw new ParserException("Separator was expected!");
//            }
//            varList.add(var);
//        }
//        return varList;
//    }
//
//    private Procedure readProcedure() {
//        Procedure procedure = new Procedure();
//
//        expectSymbol(TokenType.KEYWORD, Grammar.PROCEDURE);
//
//        procedure.setIdentifier(getIdentifier());
//
//        expectSymbol(TokenType.SEPARATOR, Grammar.SEMICOLON);
//
//        procedure.setBlock(readBlock());
//
//        expectSymbol(TokenType.SEPARATOR, Grammar.SEMICOLON);
//
//        return procedure;
//    }
//
//    private Statement readStatement() {
//        if (parserHelper.isType(TokenType.KEYWORD)) {
//            switch (Grammar.getType(parserHelper.peekValue().toLowerCase())) {
//            case CALL: {
//                return readCallStatement();
//            }
//            case READ: {
//                return readReadStatement();
//
//            }
//            case WRITE: {
//                return readWriteStatement();
//
//            }
//            case BEGIN: {
//                return readBeginEndStatement();
//
//            }
//            case IF: {
//                return readIfStatement();
//
//            }
//            case WHILE: {
//                return readWhileStatement();
//
//            }
//            default:
//                throw new ParserException("Non valid word: \"" + parserHelper.pop() + "\"");
//            }
//
//        } else if (parserHelper.isType(TokenType.IDENTIFIER)) {
//
//            return readSetStatement();
//        } else {
//            throw new ParserException("Non valid word: \"" + parserHelper.pop() + "\"");
//        }
//    }
//
//    private Statement readSetStatement() {
//        SetStatement statement = new SetStatement(Grammar.ASSIGMENT);
//
//        statement.setIdentifier(getIdentifier());
//
//        expectSymbol(TokenType.ASSIGMENT, Grammar.ASSIGMENT);
//
//        statement.setExpression(readExpression());
//
//        return statement;
//    }
//
//    private Statement readCallStatement() {
//        CallStatement statement = new CallStatement(Grammar.CALL);
//
//        parserHelper.pop();
//        statement.setIdentifier(getIdentifier());
//
//        return statement;
//    }
//
//    private Statement readReadStatement() {
//        expectSymbol(TokenType.KEYWORD, Grammar.READ);
//        ReadStatement statement = new ReadStatement(Grammar.READ);
//
//        parserHelper.pop();
//        statement.setIdentifier(getIdentifier());
//
//        return statement;
//    }
//
//    private Statement readWriteStatement() {
//        expectSymbol(TokenType.KEYWORD, Grammar.WRITE);
//        WriteStatement statement = new WriteStatement(Grammar.WRITE);
//
//        statement.setIdentifier(getIdentifier());
//
//        return statement;
//    }
//
//    private Statement readBeginEndStatement() {
//        expectSymbol(TokenType.KEYWORD, Grammar.BEGIN);
//        BeginEndStatement statement = new BeginEndStatement(Grammar.BEGIN);
//
//        boolean run = true;
//        while (run) {
//            statement.addStatement(readStatement());
//
//            if (parserHelper.isSame(TokenType.SEPARATOR, Grammar.SEMICOLON)) {
//                expectSymbol(TokenType.SEPARATOR, Grammar.SEMICOLON);
//            } else if (parserHelper.isSame(TokenType.KEYWORD, Grammar.END)) {
//                expectSymbol(TokenType.KEYWORD, Grammar.END);
//                run = false;
//            } else {
//                throw new ParserException("\"END\" or \";\" expected.");
//            }
//        }
//
//        return statement;
//    }
//
//    private Statement readIfStatement() {
//        IfStatement statement = new IfStatement(Grammar.IF);
//
//        expectSymbol(TokenType.KEYWORD, Grammar.IF);
//        statement.setCondition(readCondition());
//
//        expectSymbol(TokenType.KEYWORD, Grammar.THEN);
//        statement.setStatement(readStatement());
//
//        return statement;
//
//    }
//
//    private Statement readWhileStatement() {
//        WhileStatement statement = new WhileStatement(Grammar.WHILE);
//        expectSymbol(TokenType.KEYWORD, Grammar.WHILE);
//
//        statement.setCondition(readCondition());
//
//        expectSymbol(TokenType.KEYWORD, Grammar.DO);
//
//        statement.setStatement(readStatement());
//
//        return statement;
//
//    }
//
//    private Condition readCondition() {
//        if (parserHelper.isSame(TokenType.KEYWORD, Grammar.ODD)) {
//            return readOddCondition();
//        } else {
//            return readBasicCondition();
//        }
//    }
//
//    private Condition readBasicCondition() {
//        BasicCondition condition = new BasicCondition(Grammar.HASH);
//
//        condition.setFirsExpression(readExpression());
//
//        if (new LexerHelper().isThisLogicalOperator(parserHelper.peekValue())) {
//            condition.setOperator(parserHelper.pop());
//        } else {
//            throw new ParserException("Invalid condition evaluator: \"" + parserHelper.peekValue() + "\"!");
//        }
//
//        condition.setSecondExpression(readExpression());
//
//        return condition;
//    }
//
//    private Condition readOddCondition() {
//        expectSymbol(TokenType.KEYWORD, Grammar.ODD);
//
//        OddCondition condition = new OddCondition(Grammar.ODD);
//        condition.setExpression(readExpression());
//
//        return condition;
//    }
//
//    private Expression readExpression() {
//        Expression expression = new Expression();
//
//        String prefix = isPeekEqual("+") || isPeekEqual("-") ? parserHelper.pop().getValue() : null;
//
//        expression.addTerm(readTerm(), prefix);
//
//        while (isPeekEqual("+") || isPeekEqual("-")) {
//            String operator = parserHelper.pop().getValue();
//            expression.addTerm(readTerm(), operator);
//        }
//
//        return expression;
//    }
//
//    private boolean isPeekEqual(String s) {
//        return parserHelper.peekValue().equals(s);
//    }
//
//    private Term readTerm() {
//        Term term = new Term();
//
//        term.addFactor(readFactor(), null);
//
//        while (isPeekEqual("/") || isPeekEqual("*")) {
//            String operator = parserHelper.pop().getValue();
//            term.addFactor(readFactor(), operator);
//        }
//        return term;
//    }
//
//    private Factor readFactor() {
//        Factor factor = new Factor();
//
//        if (parserHelper.peekType().equals(TokenType.IDENTIFIER)) {
//            factor.setIdentifier(getIdentifier());
//        } else if (parserHelper.peekType().equals(TokenType.LITERAL)) {
//            factor.setLiteral(getLiteral());
//        } else {
//            factor.setExpression(readExpression());
//        }
//
//        return factor;
//    }
//
//    private String getIdentifier() {
//        if (parserHelper.isType(TokenType.IDENTIFIER)) {
//            return parserHelper.pop().getValue();
//        } else {
//            throw new ParserException("Identifier was expected!");
//        }
//    }
//
//    private int getLiteral() {
//        if (parserHelper.peekType().equals(TokenType.LITERAL)) {
//            return Integer.parseInt(parserHelper.pop().getValue());
//        } else {
//            throw new ParserException("Number was expected!");
//        }
//    }
//
//    private void expectSymbol(TokenType tokenType, Grammar word) {
//        if (parserHelper.isSame(tokenType, word)) {
//            parserHelper.pop();
//        } else {
//            throw new ParserException("\"" + word.getValue() + "\" was expected!");
//        }
//    }
//
//}
