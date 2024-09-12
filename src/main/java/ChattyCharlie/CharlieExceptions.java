package ChattyCharlie;

public class CharlieExceptions extends Exception{
    public CharlieExceptions(String message){
        super(message);
    }

    // Static factory methods for common exceptions
    public static CharlieExceptions missingDescription(CommandType command) {
        return new CharlieExceptions("Oop, the task for " + command + " cannot be empty.");
    }

    public static CharlieExceptions missingDeadline() {
        return new CharlieExceptions("When is this due?");
    }

    public static CharlieExceptions missingTimes() {
        return new CharlieExceptions("Your event is missing or incomplete!");
    }
}
