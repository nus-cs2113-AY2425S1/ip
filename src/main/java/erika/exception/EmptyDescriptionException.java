package erika.exception;

public class EmptyDescriptionException extends Exception{
    public String taskType;
    public EmptyDescriptionException(String taskType) {
        super();
        this.taskType = taskType;
    }
}
