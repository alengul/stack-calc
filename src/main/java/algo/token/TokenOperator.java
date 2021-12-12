package algo.token;

import java.util.Objects;

public class TokenOperator implements Token {

    private final Operator operator;

    public TokenOperator(Operator operator) {
        this.operator = operator;
    }

    public Operator getOperator() {
        return operator;
    }

    public int precedence() {
        switch (operator) {
            case MULTIPLICATION:
            case DIVISION:
                return 2;
            case ADDITION:
            case SUBTRACTION:
                return 1;
            default:
                return 0;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenOperator operator = (TokenOperator) o;
        return this.operator == operator.getOperator();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOperator());
    }

    @Override
    public String toString() {
        return operator.toString();
    }
}
