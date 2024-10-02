package atom.exception;

public class EmptyEventException extends RuntimeException {

    @Override
    public String getMessage() {
        String message = "Guess what's missing?? Your event task silly!!";
        return message;
    }
}
