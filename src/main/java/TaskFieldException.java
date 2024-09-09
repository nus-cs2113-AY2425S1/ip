public class TaskFieldException extends Exception {

    //Lets the user know which Task field is missing
    public TaskFieldException(String missingField) {
        super(missingField);
    }
}
