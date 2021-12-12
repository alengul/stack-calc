package algo.token.parser;

import algo.shunting.TokenException;
import algo.token.*;

import java.util.ArrayList;
import java.util.List;

public class TokenParser {

    public static List<Token> parse(String line) throws TokenException {
        List<Token> result = new ArrayList<>();
        char[] arr = line.replaceAll("\\s+", "").toCharArray();
        StringBuilder number = new StringBuilder();
        for (char item : arr) {
            if (isInteger(item)) {
                number.append(item);
            } else {
                if (!number.toString().isEmpty()) {
                    result.add(new TokenNumber(Double.valueOf(number.toString())));
                    number = new StringBuilder();
                }
                result.add(parseToken(item));
            }
        }
        if (!number.toString().isEmpty()) {
            result.add(new TokenNumber(Double.valueOf(number.toString())));
        }
        return result;
    }

    private static boolean isInteger(Character number) {
        try {
            Integer.parseInt(number.toString());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static Token parseToken(char token) throws TokenException {
        switch (token) {
            case '+':
                return new TokenOperator(Operator.ADDITION);
            case '-':
                return new TokenOperator(Operator.SUBTRACTION);
            case '*':
                return new TokenOperator(Operator.MULTIPLICATION);
            case '/':
                return new TokenOperator(Operator.DIVISION);
            case '(':
                return new TokenBracket(Bracket.LEFT_BRACKET);
            case ')':
                return new TokenBracket(Bracket.RIGHT_BRACKET);
            default:
                throw new TokenException(String.valueOf(token));
        }
    }
}
