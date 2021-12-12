package algo;

import algo.calculator.OperatorsBalancingException;
import algo.calculator.StackCalc;
import algo.shunting.BracketsBalancingException;
import algo.shunting.ShuntingAlgorithm;
import algo.shunting.TokenException;
import algo.token.Token;
import algo.token.parser.TokenParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalcTest {

    private ShuntingAlgorithm shuntingAlgorithm;
    private StackCalc stackCalc;

    @BeforeEach
    public void init() {
        shuntingAlgorithm = new ShuntingAlgorithm();
        stackCalc = new StackCalc();
    }

    private Double calculate(String line) throws TokenException, BracketsBalancingException, OperatorsBalancingException {
        List<Token> list = TokenParser.parse(line);
        List<Token> reversePolandNotation = shuntingAlgorithm.toRPN(list);
        return stackCalc.calc(reversePolandNotation);
    }

    @Test
    public void CalcTest1() throws TokenException, BracketsBalancingException, OperatorsBalancingException {
        Double result = calculate("1/5 + 3*(2 - 9)");
        assertEquals(result, Double.valueOf(-20.8));
    }

    @Test
    public void CalcTest2() throws TokenException, BracketsBalancingException, OperatorsBalancingException {
        Double result = calculate("(1 + 2*(1 + 2*(1 + 2)))");
        assertEquals(result, Double.valueOf(15));
    }

    @Test
    public void CalcTest3() throws TokenException, BracketsBalancingException, OperatorsBalancingException {
        Double result = calculate("9 + 2 * 7 - 8/2");
        assertEquals(result, Double.valueOf(19));
    }

}
