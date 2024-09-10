package nova;

import nova.task.Task;

public class MessageDisplay {

    public static final String SEPARATOR = "    ___________________________________________________________________";
    public static final String NEW_LINE = "\n     ";

    public static void displayMessage(String info) {
        System.out.println(SEPARATOR + NEW_LINE + info + "\n" + SEPARATOR);
    }

    public static void displayWelcomeMessage() {
        displayMessage("Hello! I'm Nova" + NEW_LINE + "What can I do for you?");
    }

    public static void displayByeMessage() {
        displayMessage("Bye. Hope to see you again soon!");
    }

    public static void displayMarkMessage(Task task) {
        displayMessage("Nice! I've marked this task as done:" + NEW_LINE + "  " + task.getTaskInfo());
    }

    public static void displayUnmarkMessage(Task task) {
        displayMessage("OK, I've marked this task as not done yet:" + NEW_LINE + "  " + task.getTaskInfo());
    }

    public static void displayInvalidInputMessage() {
        displayMessage("Invalid input. Please use one of the following commands:" + NEW_LINE +
                "1. list" + NEW_LINE +
                "2. mark <task number>" + NEW_LINE +
                "3. unmark <task number>" + NEW_LINE +
                "4. todo <task description>" + NEW_LINE +
                "5. deadline <task description> /by <due date>" + NEW_LINE +
                "6. event <task description> /from <start time> /to <end time>");
    }

    public static void displayInvalidInputMessage(String message, String task) {
        displayMessage(message + NEW_LINE + task);
    }

    public static void displayInvalidInputMessage(String message) {
        displayMessage(message);
    }

    public static void displaySeparator() {
        System.out.println(SEPARATOR);
    }

    public static void displayAcknowledgementMessage(String message, int numberOfTasks){
        displayMessage("Got it. I've added this task:" + NEW_LINE + "  " + message +
                NEW_LINE + "Now you have " + numberOfTasks + " tasks in the list.");
    }
}
