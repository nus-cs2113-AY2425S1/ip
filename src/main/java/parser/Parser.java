package parser;
import exception.LeginIndexOutOfBoundsException;
import exception.LeginInvalidCommandException;
import task.TaskList;
import ui.Ui;

/**
 * Pulls command from user input and calling {@code TaskList} methods to execute them
 */
public class Parser {
    private static final int COMMAND_INDEX = 0;
    private static final int INDEX_TO_MARK = 1;
    private final Ui ui = new Ui();

    private void checkIndexValidity(TaskList tasklist, int index) throws LeginIndexOutOfBoundsException {
        if (index > tasklist.getTaskCount()) {
            throw new LeginIndexOutOfBoundsException();
        }
    }

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

    /**
     * Parse the user input and retrieves the command, calling {@code selectCommand} to execute the command
     *
     * @param taskList Contains all information of current tasks
     * @param input User input into command line
     * @return {@code true} if the user command is bye
     */
    public boolean parseInput(TaskList taskList, String input) {
        String[] wordsOfInput = input.split(" ");
        String command = wordsOfInput[COMMAND_INDEX];
        return selectCommand(taskList, command, wordsOfInput, input);
    }
}