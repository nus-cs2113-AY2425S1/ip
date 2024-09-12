public class EmptyTodoDescriptionException extends BronException {
    public EmptyTodoDescriptionException() {
        super("The description of a 'todo' cannot be empty.");
    }
}