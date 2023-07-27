/*
 * Copyright (c) 2020-2021 Polyhedral Development
 *
 * The Terra Core Addons are licensed under the terms of the MIT License. For more details,
 * reference the LICENSE file in this module's root directory.
 */

package com.dfsek.terra.addons.terrascript.lexer;

public class Token {
    private final String content;
    private final TokenType type;
    private final SourcePosition start;
    
    public Token(String content, TokenType type, SourcePosition start) {
        this.content = content;
        this.type = type;
        this.start = start;
    }
    
    @Override
    public String toString() {
        return type + ": '" + content + "'";
    }
    
    public TokenType getType() {
        return type;
    }
    
    public String getContent() {
        return content;
    }
    
    public SourcePosition getPosition() {
        return start;
    }
    
    public boolean isConstant() {
        return this.type.equals(TokenType.NUMBER) || this.type.equals(TokenType.STRING) || this.type.equals(TokenType.BOOLEAN);
    }
    
    public boolean isType(TokenType type) {
        return type == getType();
    }
    
    public boolean isBinaryOperator() {
        return type.equals(TokenType.ADDITION_OPERATOR)
               || type.equals(TokenType.SUBTRACTION_OPERATOR)
               || type.equals(TokenType.MULTIPLICATION_OPERATOR)
               || type.equals(TokenType.DIVISION_OPERATOR)
               || type.equals(TokenType.EQUALS_OPERATOR)
               || type.equals(TokenType.NOT_EQUALS_OPERATOR)
               || type.equals(TokenType.LESS_THAN_OPERATOR)
               || type.equals(TokenType.GREATER_THAN_OPERATOR)
               || type.equals(TokenType.LESS_THAN_OR_EQUALS_OPERATOR)
               || type.equals(TokenType.GREATER_THAN_OR_EQUALS_OPERATOR)
               || type.equals(TokenType.BOOLEAN_OR)
               || type.equals(TokenType.BOOLEAN_AND)
               || type.equals(TokenType.MODULO_OPERATOR);
    }
    
    public boolean isStrictNumericOperator() {
        return type.equals(TokenType.SUBTRACTION_OPERATOR)
               || type.equals(TokenType.MULTIPLICATION_OPERATOR)
               || type.equals(TokenType.DIVISION_OPERATOR)
               || type.equals(TokenType.GREATER_THAN_OPERATOR)
               || type.equals(TokenType.LESS_THAN_OPERATOR)
               || type.equals(TokenType.LESS_THAN_OR_EQUALS_OPERATOR)
               || type.equals(TokenType.GREATER_THAN_OR_EQUALS_OPERATOR)
               || type.equals(TokenType.MODULO_OPERATOR);
    }
    
    public boolean isStrictBooleanOperator() {
        return type.equals(TokenType.BOOLEAN_AND)
               || type.equals(TokenType.BOOLEAN_OR);
    }
    
    public boolean isVariableDeclaration() {
        return type.equals(TokenType.TYPE_STRING)
               || type.equals(TokenType.TYPE_BOOLEAN)
               || type.equals(TokenType.TYPE_NUMBER);
    }
    
    public boolean isControlStructure() {
        return type.equals(TokenType.IF_STATEMENT)
               || type.equals(TokenType.WHILE_LOOP)
               || type.equals(TokenType.FOR_LOOP);
    }
    
    public enum TokenType {
        /**
         * Function identifier or language keyword
         */
        IDENTIFIER,
        
        /**
         * Numeric literal
         */
        NUMBER,
        /**
         * String literal
         */
        STRING,
        /**
         * Boolean literal
         */
        BOOLEAN,
        /**
         * Beginning of group
         */
        GROUP_BEGIN,
        /**
         * Ending of group
         */
        GROUP_END,
        /**
         * End of statement
         */
        STATEMENT_END,
        /**
         * Argument separator
         */
        SEPARATOR,
        /**
         * Beginning of code block
         */
        BLOCK_BEGIN,
        /**
         * End of code block
         */
        BLOCK_END,
        /**
         * assignment operator
         */
        ASSIGNMENT,
        /**
         * Boolean equals operator
         */
        EQUALS_OPERATOR,
        /**
         * Boolean not equals operator
         */
        NOT_EQUALS_OPERATOR,
        /**
         * Boolean greater than operator
         */
        GREATER_THAN_OPERATOR,
        /**
         * Boolean less than operator
         */
        LESS_THAN_OPERATOR,
        /**
         * Boolean greater than or equal to operator
         */
        GREATER_THAN_OR_EQUALS_OPERATOR,
        /**
         * Boolean less than or equal to operator
         */
        LESS_THAN_OR_EQUALS_OPERATOR,
        /**
         * Addition/concatenation operator
         */
        ADDITION_OPERATOR,
        /**
         * Subtraction operator
         */
        SUBTRACTION_OPERATOR,
        /**
         * Multiplication operator
         */
        MULTIPLICATION_OPERATOR,
        /**
         * Division operator
         */
        DIVISION_OPERATOR,
        /**
         * Modulo operator.
         */
        MODULO_OPERATOR,
        /**
         * Boolean not operator
         */
        BOOLEAN_NOT,
        /**
         * Boolean or
         */
        BOOLEAN_OR,
        /**
         * Boolean and
         */
        BOOLEAN_AND,
        /**
         * Numeric variable declaration
         */
        TYPE_NUMBER,
        /**
         * String variable declaration
         */
        TYPE_STRING,
        /**
         * Boolean variable declaration
         */
        TYPE_BOOLEAN,
        /**
         * Void type declaration
         */
        TYPE_VOID,
        /**
         * If statement declaration
         */
        IF_STATEMENT,
        /**
         * While loop declaration
         */
        WHILE_LOOP,
        /**
         * Return statement
         */
        RETURN,
        /**
         * Continue statement
         */
        CONTINUE,
        /**
         * Break statement
         */
        BREAK,
        /**
         * Fail statement. Like return keyword, but specifies that generation has failed.
         */
        FAIL,
        /**
         * For loop initializer token
         */
        FOR_LOOP,
        /**
         * Else keyword
         */
        ELSE,
        /**
         * End of file
         */
        END_OF_FILE
    }
}