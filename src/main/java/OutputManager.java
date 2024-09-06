public class OutputManager {

    public static void printNumberOfTasks(int numberOfTasks) {
        System.out.println(Constants.INDENTATION + "Now you have " + numberOfTasks + " tasks in the list.");
    }

    public static void printTasksList(Task[] tasksList, int numberOfTasks) {
        System.out.println(Constants.INDENTATION + Constants.MESSAGE_TASK_LIST);
        for (int i = 0; i < numberOfTasks; i++) {
            System.out.println(Constants.INDENTATION + Integer.toString(i + 1) + ". " + tasksList[i].toString());
        }
        OutputManager.printNumberOfTasks(numberOfTasks);
    }

    public static void printWelcomeMessage() {
        System.out.println(Constants.MESSAGE_WELCOME);
    }

    public static void printExitMessage() {
        System.out.println(Constants.MESSAGE_EXIT);
    }

    public static void printLineBreak() {
        System.out.print(Constants.LINE_BREAK);
    }

    public static void printMessage(String message) {
        System.out.println(Constants.INDENTATION + message);
    }

    public static void printMessageAddedTask(String taskInfo) {
        System.out.println(Constants.INDENTATION + Constants.MESSAGE_ADDED + Constants.INDENTATION + taskInfo);
    }

    public static void printEchoInput(String input) {
        System.out.print(Constants.LINE_BREAK + Constants.INDENTATION + input + Constants.LINE_BREAK);
    }
}
