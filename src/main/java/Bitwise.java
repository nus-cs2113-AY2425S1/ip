import java.util.Scanner;

public class Bitwise {
    // ASCII art from https://ascii-generator.site/t/
    private static final String LOGO = " ____   _  _              _            \n"
            + "|  _ \\ (_)| |            (_)           \n"
            + "| |_) | _ | |_ __      __ _  ___   ___ \n"
            + "|  _ < | || __|\\ \\ / /| |/ __| / _ \\ \n"
            + "| |_) || || |_  \\ V  V / | |\\__ \\|  __/\n"
            + "|____/ |_| \\__|  \\_/\\_/  |_||___/ \\___|\n"
            + "                                       \n";
    private static final String SECTION_BREAK = "==================================================\n";
    private static final String LINE_BREAK = "--------------------------------------------------\n";
    private static final String INDENTATION = "        ";
    private static Task[] tasksList = new Task[100];
    private static int numberOfTasks = 0;

    public static void main(String[] args) {
        System.out.println(SECTION_BREAK + INDENTATION + "Hello from\n" + LOGO);
        System.out.print(INDENTATION + "How may I help you today?\n" + LINE_BREAK);
        mainManager();
        System.out.println(LINE_BREAK + INDENTATION + "Bye, see you soon!\n" + SECTION_BREAK);
    }

    public static void mainManager() {
        String userInput;
        while (true) {
            Scanner in = new Scanner(System.in);
            userInput = in.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                return;
            }
            System.out.print(LINE_BREAK);
            if (userInput.equalsIgnoreCase("list")) {
                printTasksList();
            }
            else if (userInput.contains("unmark")) {
                int taskNumber = Integer.parseInt(userInput.substring(userInput.indexOf(" ") + 1));
                markCompletionStatus(taskNumber, false);
            }
            else if (userInput.contains("mark")) {
                int taskNumber = Integer.parseInt(userInput.substring(userInput.indexOf(" ") + 1));
                markCompletionStatus(taskNumber, true);
            }
            else {
                addToList(userInput);
                System.out.println(INDENTATION + "Added: " + userInput);
            }
            System.out.print(LINE_BREAK);
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
            System.out.print(LINE_BREAK + INDENTATION + userInput + LINE_BREAK);
        }
    }

    public static void addToList(String userInput) {
        Task newTask = new Task(userInput);
        tasksList[numberOfTasks] = newTask;
        numberOfTasks++;
    }

    public static void printTasksList() {
        for (int i = 0; i < numberOfTasks; i++) {
            System.out.println(INDENTATION + Integer.toString(i + 1) + ". " + "[" + tasksList[i].getStatusIcon() + "] " + tasksList[i].getTaskName());
        }
    }

    public static void markCompletionStatus(int taskNumber, boolean isCompleted) {
        int taskIndex = taskNumber - 1;
        tasksList[taskIndex].markCompletionStatus(isCompleted);
        String message = isCompleted ? "Awesome, I've marked this task as completed!" : "I've added the task back in";
        System.out.println(INDENTATION + message);
        printTasksList();
    }
}
