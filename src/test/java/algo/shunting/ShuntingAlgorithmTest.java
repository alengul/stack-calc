package algo.shunting;

import algo.token.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShuntingAlgorithmTest {

    private ShuntingAlgorithm algorithm;

    private final Token addition = new TokenOperator(Operator.ADDITION);
    private final Token subtraction = new TokenOperator(Operator.SUBTRACTION);
    private final Token multiplication = new TokenOperator(Operator.MULTIPLICATION);
    private final Token division = new TokenOperator(Operator.DIVISION);
    private final Token leftBracket = new TokenBracket(Bracket.LEFT_BRACKET);
    private final Token rightBracket = new TokenBracket(Bracket.RIGHT_BRACKET);

    @BeforeEach
    public void init() {
        algorithm = new ShuntingAlgorithm();
    }

    @Test
    public void shouldConsiderPriorities() throws TokenException, BracketsBalancingException {
        List<Token> result = algorithm.toRPN(List.of(
                new TokenNumber(2.0),
                addition,
                new TokenNumber(2.0),
                multiplication,
                new TokenNumber(2.0)
        ));
        assertEquals(result, List.of(
                new TokenNumber(2.0),
                new TokenNumber(2.0),
                new TokenNumber(2.0),
                multiplication,
                addition
        ));
    }

    @Test
    public void shouldConsiderBrackets() throws TokenException, BracketsBalancingException {
        List<Token> result = algorithm.toRPN(List.of(
                leftBracket,
                new TokenNumber(2.0),
                addition,
                new TokenNumber(2.0),
                rightBracket,
                multiplication,
                new TokenNumber(2.0)
        ));
        assertEquals(result, List.of(
                new TokenNumber(2.0),
                new TokenNumber(2.0),
                addition,
                new TokenNumber(2.0),
                multiplication
        ));
    }

    @Test
    public void shouldThrowExceptionWhenBracketBalanceIsBroken() {
        assertThrows(BracketsBalancingException.class, () ->
                algorithm.toRPN(List.of(
                        leftBracket,
                        new TokenNumber(2.0),
                        addition,
                        new TokenNumber(2.0),
                        rightBracket,
                        rightBracket
                ))
        );

        assertThrows(BracketsBalancingException.class, () ->
                algorithm.toRPN(List.of(
                        leftBracket,
                        leftBracket,
                        new TokenNumber(2.0),
                        addition,
                        new TokenNumber(2.0),
                        rightBracket
                ))
        );
    }

    @Test
    public void shouldThrowExceptionWhenUnknownToken() {
        assertThrows(TokenException.class, () ->
                algorithm.toRPN(List.of(
                        new Token() {
                        }
                ))
        );
    }

}
