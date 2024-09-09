package exception;

public class EchoException {
    private EchoException() {}

    public static String todoDescriptionMissing() {
        return "You must provide a description for the todo task.";
    }

    public static String deadlineDescriptionMissing() {
        return "You must provide a description for the deadline task.";
    }

    public static String eventDescriptionMissing() {
        return "You must provide a description for the event task.";
    }

    public static String unknownCommand() {
        return "I'm sorry, but I don't know what that means.";
    }

    public static String taskNumberOutOfRange() {
        return "task.Task is out of range.";
    }

    public static String invalidTaskNumberFormat() {
        return "Give an integer only.";
    }
}
