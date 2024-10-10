package commandhandler;

import constants.Warnings;
import tasklist.TaskList;
import constants.Utils;
import exceptions.IllegalEmptyException;
import exceptions.IllegalIndexException;
import exceptions.IllegalKeywordException;
import exceptions.IllegalTaskException;
import exceptions.IllegalCommandException;

/** Handles the command and directs it to the relevant methods. */
public class CommandHandler {

    private final TaskList taskList;

    /**
     * CommandHandler constructor.
     */
    public CommandHandler() {
        taskList = new TaskList();
    }

    /**
     * Handles the command and directs it to the relevant methods.
     *
     * @param input A String containing the user's input.
     * @param command A String containing the user input command.
     * @param splitInputs A String[] containing the user input, split by " " delimiter.
     *
     * @throws IllegalEmptyException when the user's task description is empty.
     * @throws IllegalCommandException when the user gives an undefined command.
     * @throws IllegalTaskException when the user gives an invalid task.
     * @throws IllegalKeywordException when the user gives an invalid keyword.
     * @throws IllegalIndexException when the index of task is out of range.
     */
    public void handleCommand(String input, String command, String[] splitInputs) throws IllegalEmptyException,
            IllegalCommandException, IllegalTaskException, IllegalKeywordException, IllegalIndexException {

        switch (command) {
        case Utils.LIST:
            taskList.printList();
            break;
        case Utils.MARK:
            taskList.markItem(splitInputs);
            break;
        case Utils.UNMARK:
            taskList.unmarkItem(splitInputs);
            break;
        case Utils.TODO:
            taskList.addTodo(input);
            break;
        case Utils.DEADLINE:
            taskList.addDeadline(input);
            break;
        case Utils.EVENT:
            taskList.addEvent(input);
            break;
        case Utils.DELETE:
            taskList.deleteItem(splitInputs);
            break;
        case Utils.FIND:
            taskList.findItem(input);
            break;
        default:
            throw new IllegalCommandException(Warnings.VALID_COMMAND_WARNING);
        }
    }
}
