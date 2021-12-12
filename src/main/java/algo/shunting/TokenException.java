package algo.shunting;

import algo.token.Token;

public class TokenException extends Exception {

    public TokenException(Token token) {
        super("Token '" + token.toString() + "' is not acceptable");
    }

    public TokenException(String token) {
        super("Token '" + token + "' is not acceptable");
    }

}
