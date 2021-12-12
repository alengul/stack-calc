package algo.shunting;

public class BracketsBalancingException extends Exception {
    public BracketsBalancingException() {
        super("The balance of brackets is broken");
    }
}
