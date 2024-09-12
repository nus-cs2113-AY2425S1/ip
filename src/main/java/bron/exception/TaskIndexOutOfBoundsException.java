package bron.exception;

public class TaskIndexOutOfBoundsException extends BronException {
    public TaskIndexOutOfBoundsException() {
        super("Bron.task.Task index provided is out of bounds.");
    }
}