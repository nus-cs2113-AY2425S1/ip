import java.util.Scanner;

public class Fenix implements SampleStrings {

    public static int taskNumber = 0;
    public static Task[] taskArray = new Task[100];
    private final Scanner scanner;

    // Constructor
    public Fenix() {
        this.scanner = new Scanner(System.in);
    }

    private static String getType(String userInput) {
        try {
            return (userInput.split(" ", 2))[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    private static String getInformation(String userInput) {
        try {
            return (userInput.split(" ", 2))[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
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
        case "todo":
        case "deadline":
        case "event":
            processTasks(userInput);
            break;
        default:
            System.out.println("Please provide a valid command");
        }
        acceptUserInput();
    }

    public void bidFarewell() {
        System.out.println(FAREWELL);
        System.out.println(HORIZONTAL_LINE_USER_COMMAND);
        scanner.close();
    }

    public void showAllTasks(boolean isModified) {
        String index;
        String task;
        String extraSpace = (isModified ? "\t" : "");
        String space = extraSpace + "\t";
        for (int i = 0; i < taskArray.length && !isNullElement(i); i += 1) {
            index = (i + 1) + ". ";
            task = taskArray[i].toString();
            System.out.println(space + index + task);
        }
    }

    public boolean isNullElement(int i) {
        return taskArray[i] == null;
    }

    private void markAsDone(String[] words) {
        try {
            String taskNumber = words[1];
            if (isValidTaskNumber(taskNumber)) {
                int taskIndex = Integer.parseInt(taskNumber) - 1;
                markTaskAsDone(taskIndex);
            } else {
                System.out.println("Please provide a valid task number to mark");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please provide a task");
        }
    }

    private boolean isValidTaskNumber(String input) {
        try {
            int index = Integer.parseInt(input);
            return index > 0 && index <= taskNumber;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void unmarkAsDone(String[] words) {
        try {
            String taskNumber = words[1];
            if (isValidTaskNumber(taskNumber)) {
                int taskIndex = Integer.parseInt(taskNumber) - 1;
                unmarkTaskAsDone(taskIndex);
            } else {
                System.out.println("Please provide a valid task number to unmark");
            }
        } catch (ArrayIndexOutOfBoundsException aiobe) {
            System.out.println("Please provide a task");
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
        if (type == null || type.isBlank()) {
            System.out.println("Please provide a command");
            return;
        } else if (information == null || information.isBlank()) {
            System.out.println("Please provide a task");
            return;
        }
        Task task = returnTaskObject(type, information);
        if (task == null) {
            return;
        }
        storeTask(task);
        System.out.println(HORIZONTAL_LINE_FENIX_MODIFICATION);
        System.out.println("\t\t" + ADD + task);
        System.out.println(HORIZONTAL_LINE_FENIX_MODIFICATION);
        System.out.println("You now have " + taskNumber + " tasks awaiting your attention.");
    }

    private Task returnTaskObject(String type, String information) {
        switch (type) {
        case "todo":
            return new Todo(information);
        case "deadline":
            try {
                return new Deadline(information);
            } catch (IllegalArgumentException | FenixException e) {
                System.out.println(e.getMessage());
                return null;
            }
        case "event":
            try {
                return new Event(information);
            } catch (IllegalArgumentException | FenixException e) {
                System.out.println(e.getMessage());
                return null;
            }
        default:
            System.out.println("Please provide a valid command for the task type");
            return null;
        }
    }

    public void storeTask(Task task) {
        taskArray[taskNumber] = task;
        taskNumber++;
    }
}
