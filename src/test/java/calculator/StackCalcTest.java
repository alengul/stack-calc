package calculator;

import algo.calculator.StackCalc;
import algo.token.Operator;
import algo.token.Token;
import algo.token.TokenNumber;
import algo.token.TokenOperator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class StackCalcTest {

    private final StackCalc stackCalc = new StackCalc();

    private final Token addition = new TokenOperator(Operator.ADDITION);
    private final Token subtraction = new TokenOperator(Operator.SUBTRACTION);
    private final Token multiplication = new TokenOperator(Operator.MULTIPLICATION);
    private final Token division = new TokenOperator(Operator.DIVISION);

    @Test
    public void addition() {
        Double result = stackCalc.calc(List.of(
                new TokenNumber(1.0),
                new TokenNumber(2.0),
                addition
        ));
        assertEquals(result, Double.valueOf(1 + 2));
    }

    @Test
    public void subtraction() {
        Double result = stackCalc.calc(List.of(
                new TokenNumber(1.0),
                new TokenNumber(2.0),
                subtraction
        ));
        assertEquals(result, Double.valueOf(1 - 2));
    }

    @Test
    public void multiplication() {
        Double result = stackCalc.calc(List.of(
                new TokenNumber(3.0),
                new TokenNumber(2.0),
                multiplication
        ));
        assertEquals(result, Double.valueOf(3 * 2));
    }

    @Test
    public void division() {
        Double result = stackCalc.calc(List.of(
                new TokenNumber(3.0),
                new TokenNumber(2.0),
                division
        ));
        assertEquals(result, Double.valueOf(3.0 / 2.0));
    }

    @Test
    public void shouldGiveSameResultOnEqualRPN() {
        Double result1 = stackCalc.calc(List.of(
                new TokenNumber(10.0),
                new TokenNumber(15.0),
                subtraction,
                new TokenNumber(3.0),
                multiplication
        ));

        Double result2 = stackCalc.calc(List.of(
                new TokenNumber(3.0),
                new TokenNumber(10.0),
                new TokenNumber(15.0),
                subtraction,
                multiplication
        ));

        assertEquals(result1, result2);
    }

}
