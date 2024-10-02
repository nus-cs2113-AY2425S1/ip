package atom.exception;

public class AtomException extends RuntimeException{

    @Override
    public String getMessage() {
        String message = "Whoops!! Something went wrong... :(";
        return message;
    }
}
