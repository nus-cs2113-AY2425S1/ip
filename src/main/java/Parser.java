import exception.LeginIndexOutOfBoundsException;
import exception.LeginInvalidCommandException;

/**
 * Pulls command from user input and calling {@code TaskList} methods to execute them
 */
public class Parser {
    private final int COMMAND_INDEX = 0;
    private final int INDEX_TO_MARK = 1;
    private Ui ui = new Ui();

    /**
     * Checks if the index that was input by the user is out of bounds
     *
     * @param tasklist {@code TaskList} member which keeps track of number of task and stores all the tasks
     * @param index Index of the task in the list of tasks to check
     * @throws LeginIndexOutOfBoundsException If index is out of range of task list array
     */
    private void checkIndexValidity(TaskList tasklist, int index) throws LeginIndexOutOfBoundsException {
        if (index > tasklist.getTaskCount()) {
            throw new LeginIndexOutOfBoundsException();
        }
    }

    /**
     * Parse the user input and retrieves the command, calling {@code selectCommand} to execute the command
     *
     * @param taskList
     * @param input User input into command line
     * @return {@code true} if the user command is bye
     */
    public boolean parseInput(TaskList taskList, String input) {
        String[] wordsOfInput = input.split(" ");
        String command = wordsOfInput[COMMAND_INDEX];
        return selectCommand(taskList, command, wordsOfInput, input);
    }

    /**
     * Executes the different {@code TaskList} operations according to the {@code command} param.
     *
     * @param taskList
     * @param command User command parsed from {@code parseInput}
     * @param words Array of words from the user input
     * @param input User input into command line
     * @return {@code true} if the user command is bye
     */
    private boolean selectCommand(TaskList taskList, String command, String[] words, String input) {
        int indexOfTaskToMark;
        try {
            switch (command) {
            case "bye":
                return true;
            case "list":
                taskList.listAllTask();
                break;
            case "mark":
                indexOfTaskToMark = Integer.parseInt(words[INDEX_TO_MARK]);
                checkIndexValidity(taskList, indexOfTaskToMark);
                taskList.markTask(indexOfTaskToMark);
                break;
            case "unmark":
                indexOfTaskToMark = Integer.parseInt(words[INDEX_TO_MARK]);
                checkIndexValidity(taskList, indexOfTaskToMark);
                taskList.unmarkTask(indexOfTaskToMark);
                break;
            case "todo":
                taskList.addTodo(input);
                break;
            case "deadline":
                taskList.addDeadline(input);
                break;
            case "event":
                taskList.addEvent(input);
                break;
            case "delete":
                int indexOfTaskToDelete = Integer.parseInt(words[1]);
                checkIndexValidity(taskList, indexOfTaskToDelete);
                taskList.deleteTask(indexOfTaskToDelete);
                break;
            case "find":
                taskList.findAllMatchingTask(input);
                break;
            default:
                throw new LeginInvalidCommandException();
            }
        } catch (LeginInvalidCommandException | LeginIndexOutOfBoundsException e) {
            ui.printExceptionMessage(e);
        }
        return false;
    }
}