import java.util.Scanner;

public class Bitwise {
    // ASCII art from https://ascii-generator.site/t/
    private static final String logo = " ____   _  _              _            \n"
            + "|  _ \\ (_)| |            (_)           \n"
            + "| |_) | _ | |_ __      __ _  ___   ___ \n"
            + "|  _ < | || __|\\ \\ / /| |/ __| / _ \\ \n"
            + "| |_) || || |_  \\ V  V / | |\\__ \\|  __/\n"
            + "|____/ |_| \\__|  \\_/\\_/  |_||___/ \\___|\n"
            + "                                       \n";
    private static final String sectionBreak = "==================================================\n";
    private static final String lineBreak = "--------------------------------------------------\n";
    private static final String indentation = "        ";
    private static String[] taskList = new String[100];
    private static int numberOfTasks = 0;
    private static boolean[] isCompleted = new boolean[100];

    public static void main(String[] args) {
        System.out.println(sectionBreak + indentation + "Hello from\n" + logo);
        System.out.print(indentation + "How may I help you today?\n" + lineBreak);
        mainManager();
        System.out.println(lineBreak + indentation + "Bye, see you soon!\n" + sectionBreak);
    }
    public static void mainManager() {
        String userInput;
        while (true) {
            Scanner in = new Scanner(System.in);
            userInput = in.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                return;
            }
            System.out.print(lineBreak);
            if (userInput.equalsIgnoreCase("list")) {
                printTaskList();
            }
            else if (userInput.contains("unmark")) {
                String taskName = userInput.substring(userInput.indexOf(" ") + 1);
                markCompletionStatus(taskName, false);
            }
            else if (userInput.contains("mark")) {
                String taskName = userInput.substring(userInput.indexOf(" ") + 1);
                markCompletionStatus(taskName, true);
            }
            else {
                addToList(userInput);
                System.out.println(indentation + "Added: " + userInput);
            }
            System.out.print(lineBreak);
        }
    }
    public static void echoUserInput() {
        String userInput;
        while (true) {
            Scanner in = new Scanner(System.in);
            userInput = in.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                return;
            }
            System.out.print(lineBreak + indentation + userInput + lineBreak);
        }
    }
    public static void addToList(String userInput) {
        taskList[numberOfTasks] = userInput;
        numberOfTasks++;
    }
    public static void printTaskList() {
        for (int i = 0; i < numberOfTasks; i++) {
            char completionChar = isCompleted[i] ? 'X' : ' ';
            System.out.println(indentation + Integer.toString(i + 1) + ". " + "[" + completionChar + "] " + taskList[i]);
        }
    }
    public static void markCompletionStatus(String taskName, boolean isComplete) {
        for (int i = 0; i < numberOfTasks; i++) {
            if (taskList[i].equalsIgnoreCase(taskName)) {
                isCompleted[i] = isComplete;
            }
        }
        String message = isComplete ? "Awesome, I've marked this task as completed!" : "I've added the task back in";
        System.out.println(indentation + message);
        printTaskList();
    }
}
