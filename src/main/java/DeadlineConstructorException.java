public class DeadlineConstructorException extends Exception {
    public DeadlineConstructorException(String message) {
        super("DEADLINE CONSTRUCTOR EXCEPTION: " + errorMessage(message));
        Task.deleteLatestTask();
    }

    private static String errorMessage(String message) {
        if (!(message.contains(" /by "))) {
            return "MISSING BY COMMAND";
        }
        String[] taskStringBreakdown = message.replace("deadline ", "").split(" /by ");
        if (taskStringBreakdown[0].isEmpty()) {
            return "MISSING TASK STATEMENT";
        }
        return "UNKNOWN ERROR";
    }
}
