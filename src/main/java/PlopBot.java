import java.util.Scanner;
import java.util.ArrayList;

public class PlopBot {

    // Static variables
    private static final String name = "plopBot";
    private static final String HORIZONTAL_LINE = "//" + "\u2500".repeat(50);
    private static final String ECHO_LINE = "    " + "\u2500".repeat(48);
    private static Scanner scanner = new Scanner(System.in);

    private static ArrayList<Task> tasks;
    private static String userName;

    public static void main(String[] args) {
        welcomeMessage();
        tasks = new ArrayList<>();
        inputReader();
    }

    /**
     * Welcome message when the user runs the program.
     */
    static void welcomeMessage() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm " + name + ".");
        System.out.println("What can I do for you today?\n");
    }

    /**
     * Farewell message when the user ends the program.
     */
    static void farewellMessage() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Thank you for choosing " + name + ". Have a great day!\n");
    }

    /**
     * Reads the user's inputs and calls processInput()
     */
    static void inputReader() {
        String userInput;

        while (true) {
            userInput = scanner.nextLine().trim();
            // processInput(userInput);

            if (processInput(userInput)) {
                break;
            }
        }

        scanner.close();
    }

    /**
     * Processes the user's inputs.
     * @param userInput
     */
    static boolean processInput(String userInput) {
        if (userInput.toLowerCase().startsWith("mark") || userInput.toLowerCase().startsWith("unmark")) {
            toggleMark(userInput);
            return false;
        }

        switch (userInput.toLowerCase()) {
            case "list":
                list();
                break;
            case "help":
                showHelp();
                break;
            case "clear":
                tasks.clear();
                System.out.println("All tasks cleared.");
                break;
            case "mark":
                toggleMark(userInput);
                break;
            case "bye", "exit", "quit":
                farewellMessage();
                return true;

            default:
                tasks.add(new Task(userInput));
                System.out.println("    Added: " + userInput);
                break;
        }

        return false;
    }

    /**
     * Lists the user's inputs.
     */
    static void list() {
        System.out.println(ECHO_LINE);
        System.out.println("    Your List:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.printf("    %d.%s %s%n", i + 1, task.getStatusIcon(), task.getName());
        }
        System.out.println("");
    }

    static void toggleMark(String userInput) {
        try {
            int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;

            if (taskNumber >= 0 && taskNumber < tasks.size()) {
                Task task = tasks.get(taskNumber);
                task.toggleStatus();
                System.out.println(ECHO_LINE);
                System.out.printf("    Successfully updated task %d.\n", taskNumber + 1);
                System.out.println("    Your Updated List:");

                for (int i = 0; i < tasks.size(); i++) {
                    Task t = tasks.get(i);
                    System.out.printf("    %d.%s %s%n", i + 1, t.getStatusIcon(), t.getName());
                }
            }
        }
        catch (NumberFormatException | ArrayIndexOutOfBoundsException e){
            System.out.println("Invalid command. Please use 'mark <task number>'.");
        }
    }

    static void showHelp() {
        System.out.println("");
    }
}

