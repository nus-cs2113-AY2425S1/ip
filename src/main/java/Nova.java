import java.util.Scanner;


public class Nova {

    private static final int MAX_TASKS = 100;
    public static final String SEPARATOR = "    ___________________________________________________________________";
    public static final String NEW_LINE = "\n     ";

    /**
     * The main method is the entry point of the program. It initializes the task list
     * and continuously processes user input until the user exits the program.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        displayWelcomeMessage();
        Task[] tasks = new Task[MAX_TASKS];
        Scanner sc = new Scanner(System.in);

        while (true) {
            String[] userInput = getUserInput(sc);
            if (handleInput(userInput, tasks)) {
                return;
            }
        }
    }

    /**
     * Displays a message enclosed by a separator for better readability.
     *
     * @param info The message to be displayed.
     */
    public static void displayMessage(String info) {
        System.out.println(SEPARATOR + NEW_LINE + info + "\n" + SEPARATOR);
    }

    private static void displayWelcomeMessage() {
        displayMessage("Hello! I'm Nova" + NEW_LINE + "What can I do for you?");
    }

    private static String[] getUserInput(Scanner sc) {
        if (!sc.hasNextLine()) {
            return new String[]{"bye"};
        }
        String info = sc.nextLine();
        return info.split(" ", 2);
    }

    private static boolean handleInput(String[] inputs, Task[] tasks) {

        switch (inputs[0]) {

        case "bye":
            return handleByeCase();

        case "list":
            handleListCase(tasks);
            break;

        case "mark":
        case "unmark":
            handleMarkAndUnmarkCase(inputs, tasks);
            break;

        case "todo":
            handleTodoCase(inputs, tasks);
            break;

        case "deadline":
            handleDeadlineCase(inputs, tasks);
            break;

        case "event":
            handleEventCase(inputs, tasks);
            break;

        default:
            displayMessage("Invalid input. Please use one of the following commands:" + NEW_LINE +
                    "1. list" + NEW_LINE +
                    "2. mark <task number>" + NEW_LINE +
                    "3. unmark <task number>" + NEW_LINE +
                    "4. todo <task description>" + NEW_LINE +
                    "5. deadline <task description> /by <due date>" + NEW_LINE +
                    "6. event <task description> /from <start time> /to <end time>");
            break;

        }
        return false;
    }

    private static boolean handleByeCase() {
        displayMessage("Bye. Hope to see you again soon!");
        return true;
    }

    private static void handleListCase(Task[] tasks) {
        System.out.println(SEPARATOR);
        for (int i = 1; i <= Task.getNumberOfTasks(); i++) {
            System.out.println("     " + i + "." + tasks[i - 1].getTaskInfo());
        }
        System.out.println(SEPARATOR);
    }

    private static void handleMarkAndUnmarkCase(String[] words, Task[] tasks) {
        int taskIndex = validateIndex(words);
        if (taskIndex == -1) return;

        if (words[0].equals("mark")) {
            markTask(tasks, taskIndex);
        } else {
            unmarkTask(tasks, taskIndex);
        }
    }

    private static int validateIndex(String[] words) {
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(words[1]);
        } catch (NumberFormatException e) { //Display error if user did not put a number as the second argument
            displayMessage("Invalid index. Please provide a valid task number after the command.");
            return -1;
        } catch (ArrayIndexOutOfBoundsException e) { //Display error if user did not input second argument
            displayMessage("Missing index argument" + NEW_LINE + "Usage: <command> <task number>");
            return -1;
        }

        if (taskIndex <= 0 || taskIndex > Task.getNumberOfTasks()) { //Display error if index isInvalid
            displayMessage("Invalid task index: " + taskIndex + ". Please provide a number between 1 and " +
                    Task.getNumberOfTasks() + ".");
            return -1;
        }

        return taskIndex;
    }

    private static void markTask(Task[] tasks, int taskIndex) {
        tasks[taskIndex - 1].markDone();
        displayMessage("Nice! I've marked this task as done:" + NEW_LINE + "  " +
                tasks[taskIndex - 1].getTaskInfo());
    }

    private static void unmarkTask(Task[] tasks, int taskIndex) {
        tasks[taskIndex - 1].unmarkDone();
        displayMessage("OK, I've marked this task as not done yet:" + NEW_LINE + "  " +
                tasks[taskIndex - 1].getTaskInfo());
    }

    private static void handleTodoCase(String[] words, Task[] tasks) {
        if (words.length != 2) {
            displayMessage("Invalid input for 'todo'." + NEW_LINE + "Usage: todo <task description>");
            return;
        }
        tasks[Task.getNumberOfTasks()] = new Todo(words[1]);
    }

    private static void handleDeadlineCase(String[] words, Task[] tasks) {
        words = validateDeadlineInput(words);
        if (words == null) {
            return;
        }
        tasks[Task.getNumberOfTasks()] = new Deadline(words[0], words[1]);
    }

    private static String[] validateDeadlineInput(String[] input) {
        String[] splitInput = new String[2];
        final String errorMessage = "Invalid input for 'deadline'." + NEW_LINE +
                "Usage: deadline <task description> /by <due date>";
        if (input.length != 2) {
            displayMessage(errorMessage);
            return null;
        }
        input = input[1].split(" /by ");
        if (input.length != 2) {
            displayMessage(errorMessage);
            return null;
        }
        splitInput[0] = input[0];
        splitInput[1] = input[1];
        return splitInput;
    }

    private static void handleEventCase(String[] words, Task[] tasks) {
        words = validateEventInput(words);
        if (words == null) {
            return;
        }
        tasks[Task.getNumberOfTasks()] = new Event(words[0], words[1], words[2]);
    }

    private static String[] validateEventInput(String[] input) {
        String[] splitInput = new String[3];
        final String errorMessage = "Invalid input for 'event'." + NEW_LINE +
                "Usage: event <task description> /from <start time> /to <end time>";
        if (input.length != 2) {
            displayMessage(errorMessage);
            return null;
        }
        input = input[1].split(" /from ");
        if (input.length != 2) {
            displayMessage(errorMessage);
            return null;
        }
        splitInput[0] = input[0];
        input = input[1].split(" /to ");
        if (input.length != 2) {
            displayMessage(errorMessage);
            return null;
        }
        splitInput[1] = input[0];
        splitInput[2] = input[1];
        return splitInput;
    }
}
