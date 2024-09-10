public class EventConstructorException extends Exception {
    public EventConstructorException(String message) {
        super("EVENT CONTRUCTOR EXCEPTION: " + message);
        Task.deleteLatestTask();
    }
    private static String errorMessage(String message) {
        if (!(message.contains(" /from ") | message.contains(" /to "))) {
            return "MISSING FROM/TO COMMANDS";
        }
        String[] taskStringBreakdown = message.replace("event ", "").split(" /from ");
        if (taskStringBreakdown[0] == "" | taskStringBreakdown[0] == null) {
            return "MISSING TASK STATEMENT";
        }
        String[] fromToStringBreakdown = taskStringBreakdown[1].split(" /to ");
        if (fromToStringBreakdown.length != 2){
            return "MISSING FROM/TO DATES";
        }
        return "UNKNOWN ERROR";
    }
}
