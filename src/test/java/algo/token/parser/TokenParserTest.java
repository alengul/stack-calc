package algo.token.parser;

import algo.shunting.TokenException;
import algo.token.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TokenParserTest {

    @Test
    public void shouldRemoveWhitespacesWhenParse() throws TokenException {
        String str = "( 1 + 2)*3   ";
        assertEquals(TokenParser.parse(str),
                List.of(
                        new TokenBracket(Bracket.LEFT_BRACKET),
                        new TokenNumber(1.0),
                        new TokenOperator(Operator.ADDITION),
                        new TokenNumber(2.0),
                        new TokenBracket(Bracket.RIGHT_BRACKET),
                        new TokenOperator(Operator.MULTIPLICATION),
                        new TokenNumber(3.0)
                ));
    }

    @Test
    public void shouldParseIntegerCorrectly() throws TokenException {
        String str = "(1+234)*3";
        assertEquals(TokenParser.parse(str),
                List.of(
                        new TokenBracket(Bracket.LEFT_BRACKET),
                        new TokenNumber(1.0),
                        new TokenOperator(Operator.ADDITION),
                        new TokenNumber(234.0),
                        new TokenBracket(Bracket.RIGHT_BRACKET),
                        new TokenOperator(Operator.MULTIPLICATION),
                        new TokenNumber(3.0)
                ));
    }
}
