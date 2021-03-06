package algo.token;

import java.util.Objects;

public class TokenBracket implements Token {

    private final Bracket bracket;

    public TokenBracket(Bracket bracket) {
        this.bracket = bracket;
    }

    public Bracket getBracket() {
        return bracket;
    }

    public int precedence() {
        if (bracket == Bracket.LEFT_BRACKET) {
            return -1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenBracket bracket = (TokenBracket) o;
        return this.bracket == bracket.getBracket();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBracket());
    }

    @Override
    public String toString() {
        return bracket.toString();
    }
}
