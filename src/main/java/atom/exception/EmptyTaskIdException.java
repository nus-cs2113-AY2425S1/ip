package atom.exception;

public class EmptyTaskIdException extends RuntimeException {

    @Override
    public String getMessage() {
        String message = "Please specify the task id!!";
        return message;
    }
}
