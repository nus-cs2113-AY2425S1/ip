public class DeadlineConstructorException extends Exception {
    public DeadlineConstructorException(String message) {
        super("DEADLINE CONTRUCTOR EXCEPTION: " + message);
        Task.deleteLatestTask();
    }

    private static String errorMessage(String message) {
        if (!(message.contains(" /by "))) {
            return "MISSING BY COMMAND";
        }
        String[] taskStringBreakdown = message.replace("event ", "").split(" /by ");
        if (taskStringBreakdown[0] == "" | taskStringBreakdown[0] == null) {
            return "MISSING TASK STATEMENT";
        }
        return "UNKNOWN ERROR";
    }
}
