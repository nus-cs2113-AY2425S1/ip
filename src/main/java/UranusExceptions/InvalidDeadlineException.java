package UranusExceptions;

public class InvalidDeadlineException extends UranusExceptions {
    public InvalidDeadlineException() {
        super();
    }

    @Override
    public String getMessage() {
        return "Deadline format is invalid! \n" +
                "Correct format 1: deadline <task> /by <deadline> \n" +
                "Correct format 2: deadline <task> /by <date in dd-MM-yyyy> <time in HHmm>";
    }
}
