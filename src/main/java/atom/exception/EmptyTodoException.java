package atom.exception;

public class EmptyTodoException extends RuntimeException {

    @Override
    public String getMessage() {
        String message = "Erm... what's the name of the task again??";
        return message;
    }
}
