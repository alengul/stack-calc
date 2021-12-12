package algo;

import algo.calculator.StackCalc;
import algo.token.Token;
import algo.token.parser.TokenParser;
import algo.shunting.BracketsBalancingException;
import algo.shunting.ShuntingAlgorithm;
import algo.shunting.TokenException;

import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws TokenException, BracketsBalancingException {
        String line = scanner.nextLine();
        List<Token> list = TokenParser.parse(line);
        ShuntingAlgorithm shuntingAlgorithm = new ShuntingAlgorithm();
        List<Token> reversePolandNotation = shuntingAlgorithm.toRPN(list);
        StackCalc calculator = new StackCalc();
        Double result = calculator.calc(reversePolandNotation);
        System.out.println(result);
    }
}
