package algo.calculator;


import algo.token.Operator;
import algo.token.Token;
import algo.token.TokenNumber;
import algo.token.TokenOperator;

import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

public class StackCalc {

    private final Stack<Double> stack = new Stack<>();

    public Double calc(List<Token> reversePolishNotation) throws OperatorsBalancingException {
        for (Token token : reversePolishNotation) {
            if (token instanceof TokenNumber) {
                onNumber(((TokenNumber) token).getNumber());
            } else {
                onOperator(((TokenOperator) token).getOperator());
            }
        }
        return stack.peek();
    }

    private void onNumber(Double number) {
        stack.push(number);
    }

    private void onOperator(Operator operator) throws OperatorsBalancingException {
        try {
            Double a = stack.pop();
            Double b = stack.pop();
            switch (operator) {
                case ADDITION:
                    stack.push(a + b);
                    break;
                case SUBTRACTION:
                    stack.push(b - a);
                    break;
                case DIVISION:
                    stack.push(b / a);
                    break;
                case MULTIPLICATION:
                    stack.push(a * b);
            }
        } catch (EmptyStackException e) {
            throw new OperatorsBalancingException();
        }
    }
}
