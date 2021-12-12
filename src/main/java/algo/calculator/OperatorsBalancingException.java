package algo.calculator;

public class OperatorsBalancingException extends Exception{
    public OperatorsBalancingException() {
        super("The balance of operators is broken");
    }
}
