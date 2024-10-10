package bron.exception;

public class EmptyKeywordException extends BronException {
    public EmptyKeywordException() {
        super("Missing Keyword");
    }
}
