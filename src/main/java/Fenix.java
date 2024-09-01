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
    public static String[] taskArray = new String[100];
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
        switch (userInput) {
        case "bye":
            bidFarewell();
            break;
        case "list":
            showAllTasks();
            acceptUserInput();
            break;
        default:
            storeTask(userInput);
            acceptUserInput();
            break;
        }
    }

    public void bidFarewell() {
        System.out.println(FAREWELL);
        System.out.println(HORIZONTAL_LINE_USER_COMMAND);
        scanner.close();
    }

    public void showAllTasks() {
        for (int i = 0; i < taskArray.length && taskArray[i] != null; i += 1) {
            System.out.println("\t" + i + ". " + taskArray[i]);
        }
    }

    public void storeTask(String userInput) {
        taskArray[taskNumber] = userInput;
        taskNumber += 1;
        System.out.println(HORIZONTAL_LINE_FENIX_MODIFICATION);
        System.out.println("\t\t" + ADD + userInput);
        System.out.println(HORIZONTAL_LINE_FENIX_MODIFICATION);
    }
}
