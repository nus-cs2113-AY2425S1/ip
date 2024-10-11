/**
 * The Parser class is responsible for interpreting user input and executing the corresponding commands.
 * It also provides methods to create task objects based on user input and validates task numbers.
 */
public class Parser {
    private Fenix fenix;

    /**
     * Constructs a Parser object and associates it with a given Fenix instance.
     *
     * @param fenix The Fenix instance that this parser will communicate with.
     */
    public Parser(Fenix fenix) {
        this.fenix = fenix;
    }

    /**
     * Processes the user's input by determining the command type and invoking the relevant method in Fenix.
     * Splits the input into a command and optional information, then handles the command accordingly.
     *
     * @param userInput The raw input from the user.
     */
    public void processUserInput(String userInput) {
        String[] words = userInput.trim().split(" ", 2);
        String commandType = words[0];
        String commandInfo = (words.length > 1) ? words[1] : "";

        switch (commandType) {
        case "bye":
            fenix.handleBye();
            break;
        case "list":
            fenix.handleList();
            break;
        case "mark":
            fenix.handleMark(commandInfo);
            break;
        case "unmark":
            fenix.handleUnmark(commandInfo);
            break;
        case "todo":
        case "deadline":
        case "event":
            fenix.handleAddTask(commandType, commandInfo);
            break;
        case "delete":
            fenix.handleDelete(commandInfo);
            break;
        default:
            fenix.handleInvalidCommand();
        }
    }

    /**
     * Creates and returns a task object (Todo, Deadline, or Event) based on the provided type and information.
     * For deadlines and events, it handles exceptions when the provided information is invalid.
     *
     * @param type The type of the task (todo, deadline, or event).
     * @param information The task details.
     * @return A Task object corresponding to the given type, or null if invalid.
     */
    public Task returnTaskObject(String type, String information) {
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
            return null;
        }
    }

    /**
     * Creates and returns a task object based on the task type, status, and information provided.
     * Used when reconstructing tasks from storage, with the task's completion status taken into account.
     *
     * @param taskType The type of the task (T for Todo, D for Deadline, E for Event).
     * @param taskStatus The completion status of the task (X for done, any other value for not done).
     * @param taskInfo The task details.
     * @return A Task object corresponding to the provided parameters, or null if invalid.
     */
    public Task returnTaskObject(String taskType, String taskStatus, String taskInfo) {
        boolean isDone = (taskStatus.equals("X"));
        return switch (taskType) {
            case "T" -> new Todo(isDone, taskInfo);
            case "D" -> new Deadline(isDone, taskInfo);
            case "E" -> new Event(isDone, taskInfo);
            default -> null;
        };
    }

    /**
     * Validates whether the provided task number is valid by checking if it is a valid integer
     * and if it lies within the bounds of the task list.
     *
     * @param taskNumber The task number to validate.
     * @return true if the task number is valid, false otherwise.
     */
    public boolean isValidTaskNumber(String taskNumber) {
        try {
            int index = parseInteger(taskNumber);
            return index > 0 && index <= fenix.getSize();
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Parses the input string to an integer.
     *
     * @param inputInteger The input string representing an integer.
     * @return The parsed integer value.
     * @throws NumberFormatException if the input string cannot be parsed as an integer.
     */
    public int parseInteger(String inputInteger) {
        return Integer.parseInt(inputInteger);
    }
}
