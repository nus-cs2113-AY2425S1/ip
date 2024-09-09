public class TaskDescriptionException extends Exception{

    //Lets the user know which task type caused the exception
    public TaskDescriptionException(String taskType) {
        super(taskType);
    }
}
