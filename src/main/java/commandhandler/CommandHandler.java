package commandhandler;

import constants.Warnings;
import tasklist.TaskList;
import constants.Utils;
import exceptions.IllegalEmptyException;
import exceptions.IllegalIndexException;
import exceptions.IllegalKeywordException;
import exceptions.IllegalTaskException;
import exceptions.IllegalCommandException;

/**
 * After extracting the relevant commands,the class also handles the command
 * and directs it to the relevant methods.
 */
public class CommandHandler {

    private final TaskList taskList;

    /**
     * CommandHandler constructor
     */
    public CommandHandler() {
        taskList = new TaskList();
    }

    /**
     *The method also handles the command and directs it to the relevant methods.
     *
     * @param input A String containing the user's input
     * @param command A String containing the user input command
     * @param splitInputs A String[] containing the user input, split by " " delimiter.
     *
     * @throws IllegalEmptyException when the user's task description is empty
     * @throws IllegalCommandException when the user gives an undefined command
     * @throws IllegalTaskException when the user gives an invalid task
     * @throws IllegalKeywordException when the user gives an invalid keyword
     * @throws IllegalIndexException when the index of task is out of range
     */
    public void handleCommand(String input, String command, String[] splitInputs) throws IllegalEmptyException,
            IllegalCommandException, IllegalTaskException, IllegalKeywordException, IllegalIndexException {
        if (command.equals(Utils.LIST)) {
            taskList.printList();
        } else if (command.equals(Utils.MARK)) {
            taskList.markItem(splitInputs);
        } else if (command.equals(Utils.UNMARK)) {
            taskList.unmarkItem(splitInputs);
        } else if (command.equals(Utils.TODO)) {
            taskList.addTodo(input);
        } else if (command.equals(Utils.DEADLINE)) {
            taskList.addDeadline(input);
        } else if (command.equals(Utils.EVENT)) {
            taskList.addEvent(input);
        } else if (command.equals(Utils.DELETE)) {
            taskList.deleteItem(splitInputs);
        } else if (command.equals(Utils.FIND)){
            taskList.findItem(input);
        } else {
            throw new IllegalCommandException(Warnings.VALID_COMMAND_WARNING);
        }
    }
}
