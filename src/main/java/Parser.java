import exception.LeginIndexOutOfBoundsException;
import exception.LeginInvalidCommandException;

/**
 * Pulls command from user input and calling {@code TaskList} methods to execute them
 */
public class Parser {
    private Ui ui = new Ui();

    /**
     * Checks if the index that was input by the user is out of bounds
     *
     * @param tasklist {@code TaskList} member which keeps track of number of task and stores all the tasks
     * @param index Index of the task in the list of tasks to check
     * @throws LeginIndexOutOfBoundsException
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
        String command = wordsOfInput[0];
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
     * @throws LeginInvalidCommandException when {@code command} does not match any {@code TaskList} operations
     */
    private boolean selectCommand(TaskList taskList, String command, String[] words, String input) {
        int indexOfTaskToMark;
        try {
            switch (command) {
            case "bye":
                return true;
            case "list":
                taskList.list();
                break;
            case "mark":
                indexOfTaskToMark = Integer.parseInt(words[1]);
                checkIndexValidity(taskList, indexOfTaskToMark);
                taskList.markTask(indexOfTaskToMark);
                break;
            case "unmark":
                indexOfTaskToMark = Integer.parseInt(words[1]);
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