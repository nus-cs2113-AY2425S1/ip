package UranusExceptions;

public class InvalidEventException extends UranusExceptions {
    public InvalidEventException() {
        super();
    }

    @Override
    public String getMessage() {
        return "Event format is invalid! \n" +
                "Correct format 1: event <task> /from <start> /to <end> \n" +
                "Correct format 2: " +
                "event <task> /from <date in dd-MM-yyyy> <time in HHmm>" +
                " /to <date in dd-MM-yyyy> <time in HHmm>";
    }
}
