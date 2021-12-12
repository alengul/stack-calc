package algo.shunting;

import algo.token.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ShuntingAlgorithm {

    private final Stack<Token> operatorsStack = new Stack<>();
    private final Stack<Integer> precedencesStack = new Stack<>();
    private final List<Token> result;

    public ShuntingAlgorithm() {
        this.result = new ArrayList<>();
    }

    public List<Token> toRPN(List<Token> arr) throws TokenException, BracketsBalancingException {
        for (Token token : arr) {
            if (token instanceof TokenNumber) {
                onNumber((TokenNumber) token);
            } else if (token instanceof TokenOperator) {
                onOperator((TokenOperator) token);
            } else if (token instanceof TokenBracket) {
                onBracket((TokenBracket) token);
            } else {
                throw new TokenException(token);
            }
        }
        onRemainOperators();
        return result;
    }

    private void onNumber(TokenNumber number) {
        result.add(number);
    }

    private void onOperator(TokenOperator operator) {
        while (!operatorsStack.isEmpty() && precedencesStack.peek() >= operator.precedence()) {
            result.add(operatorsStack.pop());
            precedencesStack.pop();
        }
        operatorsStack.push(operator);
        precedencesStack.push(operator.precedence());
    }

    private void onBracket(TokenBracket bracket) throws TokenException, BracketsBalancingException {
        if (bracket.equals(new TokenBracket(Bracket.LEFT_BRACKET))) {
            operatorsStack.push(bracket);
            precedencesStack.push(bracket.precedence());
        } else if (bracket.equals(new TokenBracket(Bracket.RIGHT_BRACKET))) {
            while (!operatorsStack.isEmpty() && !new TokenBracket(Bracket.LEFT_BRACKET).equals(operatorsStack.peek())) {
                result.add(operatorsStack.pop());
                precedencesStack.pop();
            }
            if (!operatorsStack.isEmpty()) {
                operatorsStack.pop();
                precedencesStack.pop();
            } else {
                throw new BracketsBalancingException();
            }
        } else {
            throw new TokenException(bracket);
        }
    }

    public void onRemainOperators() throws BracketsBalancingException {
        while (!operatorsStack.isEmpty()) {
            if (operatorsStack.peek().equals(new TokenBracket(Bracket.LEFT_BRACKET))) {
                throw new BracketsBalancingException();
            }
            result.add(operatorsStack.pop());
        }
    }

}
