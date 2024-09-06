import java.util.Scanner;

public class Fenix {

    public static final int HORIZONTAL_LINE_USER_COMMAND_LENGTH = 70;
    public static final int HORIZONTAL_LINE_FENIX_MODIFICATION_LENGTH = 62;
    public static final String HORIZONTAL_LINE_USER_COMMAND = "~".repeat(HORIZONTAL_LINE_USER_COMMAND_LENGTH);
    public static final String HORIZONTAL_LINE_FENIX_MODIFICATION =
            "\t\t" + "*".repeat(HORIZONTAL_LINE_FENIX_MODIFICATION_LENGTH);
    public static final String ADD = "added: ";
    public static final String GREETING = "Greetings. I am Fenix, your digital assistant.";
    public static final String SERVICE_PROMPT = "How may I be of service to you today?";
    public static final String FAREWELL = "It has been a pleasure assisting you. Farewell.";
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
            markAsDone(words);
            break;
        case "unmark":
            unmarkAsDone(words);
            break;
        default:
            processTasks(userInput);
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
            System.out.println(modifiedString + "\t" + (i + 1) + ". " + taskArray[i].toString());
        }
    }

    private void markAsDone(String[] words) {
        boolean hasTask = words.length > 1;
        if (hasTask && isValidTaskIndex(words[1])) {
            int taskIndex = Integer.parseInt(words[1]) - 1;
            markTaskAsDone(taskIndex);
        } else {
            System.out.println("Please provide a valid task number to mark");
        }
    }

    private void unmarkAsDone(String[] words) {
        boolean hasTask = words.length > 1;
        if (hasTask && isValidTaskIndex(words[1])) {
            int taskIndex = Integer.parseInt(words[1]) - 1;
            unmarkTaskAsDone(taskIndex);
        } else {
            System.out.println("Please provide a valid task number to unmark");
        }
    }

    private boolean isValidTaskIndex(String input) {
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
        System.out.println("Understood. This task has been marked as not done yet.");
        taskArray[taskNumber].unmarkAsDone();
        System.out.println(HORIZONTAL_LINE_FENIX_MODIFICATION);
        showAllTasks(true);
        System.out.println(HORIZONTAL_LINE_FENIX_MODIFICATION);
    }

    public void processTasks(String userInput) {
        String type = getType(userInput);
        String information = getInformation(userInput);
        if (type == null || information == null) {
            System.out.println("Please provide a command with more than one word");
            return;
        }
        Task task = returnTaskObject(type, information);
        if (task == null) {
            System.out.println("Please provide a valid command for the task type");
            return;
        }
        storeTask(task);
        System.out.println(HORIZONTAL_LINE_FENIX_MODIFICATION);
        System.out.println("\t\t" + ADD + task);
        System.out.println(HORIZONTAL_LINE_FENIX_MODIFICATION);
        System.out.println("You now have " + taskNumber + " tasks awaiting your attention.");
    }

    private static String getType(String userInput) {
        try {
            return (userInput.split(" ", 2))[0];
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    private static String getInformation(String userInput) {
        try {
            return (userInput.split(" ", 2))[1];
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    private Task returnTaskObject(String type, String information) {
        switch (type) {
        case "todo":
            return new Todo(information);
        case "deadline":
            return new Deadline(information);
        case "event":
            return new Event(information);
        default:
            return null;
        }
    }

    public void storeTask(Task task) {
        taskArray[taskNumber] = task;
        taskNumber++;
    }
}
