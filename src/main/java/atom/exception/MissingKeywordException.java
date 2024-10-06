package atom.exception;

public class MissingKeywordException extends RuntimeException{

    @Override
    public String getMessage() {
        String message = "No keyword?! What am I supposed to look for then??";
        return message;
    }
}
