import java.util.Scanner;

public class Fenix {

    public static final int HORIZONTAL_LINE_USER_COMMAND_LENGTH = 60;
    public static final int HORIZONTAL_LINE_FENIX_MODIFICATION_LENGTH = 52;
    public static final String HORIZONTAL_LINE_USER_COMMAND =
            "~".repeat(HORIZONTAL_LINE_USER_COMMAND_LENGTH);
    public static final String HORIZONTAL_LINE_FENIX_MODIFICATION =
            "\t\t" + "*".repeat(HORIZONTAL_LINE_FENIX_MODIFICATION_LENGTH);
    public static final String ADD = "added: ";
    public static final String GREETING =
            "Greetings. I am Fenix, your digital assistant.";
    public static final String SERVICE_PROMPT =
            "How may I be of service to you today?";
    public static final String FAREWELL =
            "It has been a pleasure assisting you. Farewell.";
    public static int taskNumber = 0;
    public static Task[] taskArray = new Task[100];
    private final Scanner scanner;

    // Constructor
    public Fenix() {
        this.scanner = new Scanner(System.in);
    }

    public void greet() {
        System.out.println(HORIZONTAL_LINE_USER_COMMAND);
        System.out.println(GREETING);
        System.out.println(SERVICE_PROMPT);
    }

    public void acceptUserInput() {
        System.out.println(HORIZONTAL_LINE_USER_COMMAND);
        String userInput = getUserInput();
        processUserInput(userInput);
    }

    private String getUserInput() {
        return scanner.nextLine();
    }

    private void processUserInput(String userInput) {
        String[] words = userInput.trim().split(" ");
        String command = words[0];
        switch (command) {
        case "bye":
            bidFarewell();
            return;
        case "list":
            showAllTasks(false);
            break;
        case "mark":
            if (words.length > 1 && isValidIndex(words[1])) {
                markTaskAsDone(Integer.parseInt(words[1]) - 1);
            } else {
                System.out.println(
                        "Please provide a valid task number to mark");
            }
            break;
        case "unmark":
            if (words.length > 1 && isValidIndex(words[1])) {
                unmarkTaskAsDone(Integer.parseInt(words[1]) - 1);
            } else {
                System.out.println(
                        "Please provide a valid task number to unmark");
            }
            break;
        default:
            storeTask(userInput);
            break;
        }
        acceptUserInput();
    }

    public void bidFarewell() {
        System.out.println(FAREWELL);
        System.out.println(HORIZONTAL_LINE_USER_COMMAND);
        scanner.close();
    }

    public void showAllTasks(boolean isModified) {
        String modifiedString = (isModified ? "\t" : "");
        for (int i = 0; i < taskArray.length && taskArray[i] != null; i += 1) {
            System.out.println(modifiedString + "\t" + (i + 1) + ". " +
                    taskArray[i].toString());
        }
    }

    private boolean isValidIndex(String input) {
        try {
            int index = Integer.parseInt(input);
            return index > 0 && index <= taskNumber;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void markTaskAsDone(int taskNumber) {
        System.out.println("Task successfully completed. A job well executed.");
        taskArray[taskNumber].markAsDone();
        System.out.println(HORIZONTAL_LINE_FENIX_MODIFICATION);
        showAllTasks(true);
        System.out.println(HORIZONTAL_LINE_FENIX_MODIFICATION);
    }

    public void unmarkTaskAsDone(int taskNumber) {
        System.out.println(
                "Understood. This task has been marked as not done yet.");
        taskArray[taskNumber].unmarkAsDone();
        System.out.println(HORIZONTAL_LINE_FENIX_MODIFICATION);
        showAllTasks(true);
        System.out.println(HORIZONTAL_LINE_FENIX_MODIFICATION);
    }

    public void storeTask(String userInput) {
        Task task = new Task(userInput);
        taskArray[taskNumber] = task;
        taskNumber += 1;
        System.out.println(HORIZONTAL_LINE_FENIX_MODIFICATION);
        System.out.println("\t\t" + ADD + userInput);
        System.out.println(HORIZONTAL_LINE_FENIX_MODIFICATION);
    }
}
