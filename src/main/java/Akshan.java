import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Akshan {

    private static final String NAME = "Akshan";
    private static final String LOGO = """
 
             ░▒▓██████▓▒░░▒▓█▓▒░░▒▓█▓▒░░▒▓███████▓▒░▒▓█▓▒░░▒▓█▓▒░░▒▓██████▓▒░░▒▓███████▓▒░ \s
            ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░\s
            ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░\s
            ░▒▓████████▓▒░▒▓███████▓▒░ ░▒▓██████▓▒░░▒▓████████▓▒░▒▓████████▓▒░▒▓█▓▒░░▒▓█▓▒░\s
            ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░      ░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░\s
            ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░      ░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░\s
            ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓███████▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░\s
            """;
    private static final Map<CommandType, Function<String, String[]>> COMMAND_PARSERS = new HashMap<>();

    static {
        COMMAND_PARSERS.put(CommandType.TODO, Akshan::parseTodo);
        COMMAND_PARSERS.put(CommandType.DEADLINE, Akshan::parseDeadline);
        COMMAND_PARSERS.put(CommandType.EVENT, Akshan::parseEvent);
    }


    /**
     * Prints a horizontal line.
     */
    private static void printLine(){
        System.out.println("_______________________________________________________________");
    }

    /**
     * Main method to run the Akshan bot.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        TaskList taskList = new TaskList();

        init();
        String line = input.nextLine();

        while (!line.equals(CommandType.BYE.getCommand())) {
            processCommand(line, taskList);
            printLine();
            line = input.nextLine();
        }
        bye();
    }

    /**
     * Initializes the bot, prints the logo and a welcome message.
     */
    private static void init(){
        System.out.println(LOGO);
        printLine();
        System.out.println("Hello! I'm " + NAME);
        System.out.println("What can I do for you?");
        printLine();
    }

    /**
     * Exit bot, prints goodbye sequence.
     */
    private static void bye(){
        System.out.println( "Bye. Hope to see you again soon!");
        printLine();
    }

    /**
     * Processes the user's command.
     *
     * @param command The user's input command.
     * @param taskList The list of tasks.
     */
    private static void processCommand(String command, TaskList taskList) {
        String[] splitInput = command.split("/");
        String commandTypeString = splitInput[0].split(" ")[0];
        CommandType commandType = CommandType.fromString(commandTypeString);

        switch (commandType) {
        case LIST:
            taskList.printList();
            break;
        case MARK:
            processMarkUnmark(splitInput[0], taskList, true);
            break;
        case UNMARK:
            processMarkUnmark(splitInput[0], taskList, false);
            break;
        case TODO:
        case DEADLINE:
        case EVENT:
            processTask(commandType, command, taskList);
            break;
        default:
            System.out.println("Uh oh, no command found in: " + command);
        }
    }

    /**
     * Processes a task command (TODO, DEADLINE, or EVENT).
     *
     * @param taskType The type of task.
     * @param command The full command string.
     * @param taskList The list of tasks.
     */
    private static void processTask(CommandType taskType, String command, TaskList taskList) {
        String[] params = COMMAND_PARSERS.get(taskType).apply(command);
        Task task = Task.createTask(taskType.getCommand(), params);
        addTaskToList(taskList, task);
    }

    /**
     * Adds a task to the task list and prints a confirmation message.
     *
     * @param taskList The list of tasks.
     * @param task The task to be added.
     */
    private static void addTaskToList(TaskList taskList, Task task) {
        taskList.addItem(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Processes the mark or unmark command.
     *
     * @param command The command string.
     * @param taskList The list of tasks.
     * @param mark True if marking, false if unmarking.
     */
    private static void processMarkUnmark(String command, TaskList taskList, boolean mark) {
        String[] parts = command.split(" ");
        if (parts.length == 2) {
            int index = Integer.parseInt(parts[1]);
            taskList.setItemStatus(index, mark);
        }
    }

    /**
     * Parses a TODO command.
     *
     * @param command The full command string.
     * @return An array containing the task description.
     */
    private static String[] parseTodo(String command) {
        return new String[]{command.split(" ", 2)[1]};
    }

    /**
     * Parses a DEADLINE command.
     *
     * @param command The full command string.
     * @return An array containing the task description and deadline.
     */
    private static String[] parseDeadline(String command) {
        String[] parts = command.split(" /by ", 2);
        return new String[]{parts[0].split(" ", 2)[1], parts[1]};
    }

    /**
     * Parses an EVENT command.
     *
     * @param command The full command string.
     * @return An array containing the event description, start time, and end time.
     */
    private static String[] parseEvent(String command) {
        String[] parts = command.split(" /", 3);
        return new String[]{
                parts[0].split(" ", 2)[1],
                parts[1].split(" ", 2)[1],
                parts[2].split(" ", 2)[1]
        };
    }
}