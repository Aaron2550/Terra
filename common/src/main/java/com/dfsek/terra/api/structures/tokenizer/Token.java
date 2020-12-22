package com.dfsek.terra.api.structures.tokenizer;

public class Token {
    private final String content;
    private final Type type;
    private final Position start;

    public Token(String content, Type type, Position start) {
        this.content = content;
        this.type = type;
        this.start = start;
    }

    public Type getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public Position getPosition() {
        return start;
    }

    @Override
    public String toString() {
        return type + ": '" + content + "'";
    }

    public boolean isConstant() {
        return this.type.equals(Type.NUMBER) || this.type.equals(Type.STRING) || this.type.equals(Type.BOOLEAN);
    }

    public boolean isBinaryOperator() {
        return type.equals(Type.ADDITION_OPERATOR)
                || type.equals(Type.SUBTRACTION_OPERATOR)
                || type.equals(Type.MULTIPLICATION_OPERATOR)
                || type.equals(Type.DIVISION_OPERATOR)
                || type.equals(Type.EQUALS_OPERATOR)
                || type.equals(Type.NOT_EQUALS_OPERATOR)
                || type.equals(Type.LESS_THAN_OPERATOR)
                || type.equals(Type.GREATER_THAN_OPERATOR)
                || type.equals(Type.LESS_THAN_OR_EQUALS_OPERATOR)
                || type.equals(Type.GREATER_THAN_OR_EQUALS_OPERATOR)
                || type.equals(Type.BOOLEAN_OR)
                || type.equals(Type.BOOLEAN_AND);
    }

    public boolean isStrictNumericOperator() {
        return type.equals(Type.SUBTRACTION_OPERATOR)
                || type.equals(Type.MULTIPLICATION_OPERATOR)
                || type.equals(Type.DIVISION_OPERATOR)
                || type.equals(Type.GREATER_THAN_OPERATOR)
                || type.equals(Type.LESS_THAN_OPERATOR)
                || type.equals(Type.LESS_THAN_OR_EQUALS_OPERATOR)
                || type.equals(Type.GREATER_THAN_OR_EQUALS_OPERATOR);
    }

    public boolean isStrictBooleanOperator() {
        return type.equals(Type.BOOLEAN_AND)
                || type.equals(Type.BOOLEAN_OR);
    }

    public enum Type {
        /**
         * Function identifier or language keyword
         */
        IDENTIFIER,

        /**
         * Language keyword
         */
        KEYWORD,
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
        BOOLEAN_AND
    }
}