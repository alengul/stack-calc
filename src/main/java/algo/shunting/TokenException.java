package algo.shunting;

public class TokenException extends Exception {

    public TokenException(String token) {
        super("Token '" + token + "' is not acceptable");
    }

}
