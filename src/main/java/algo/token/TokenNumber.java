package algo.token;

import java.util.Objects;

public class TokenNumber implements Token {

    private final Double number;

    public TokenNumber(Double number) {
        this.number = number;
    }

    public Double getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenNumber number = (TokenNumber) o;
        return Double.compare(getNumber(), number.getNumber()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber());
    }


}
