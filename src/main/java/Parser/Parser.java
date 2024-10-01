package Parser;

import constants.Utils;
import exceptions.*;
import Ui.Ui;
import constants.Warnings;
import TaskList.TaskList;

public class Parser {

    private final TaskList taskList;

    // Parser constructor
    public Parser() {
        taskList = new TaskList();
    }

    /**
     * Returns the final number of items in the list after executing the command.
     * The method also handles the command and directs it to the relevant methods.
     *
     * @param input A String containing the user input
     * @param count Current number of items in the list before executing the command
     * @param command The user input command
     * @param splitInputs A String[] containing the user input, split by " " delimeter.
     *
     * @return the number of items stored in the ArrayList after executing the command
     *
     * @throws IllegalCommandException when the user gives an undefined command
     */
    public int handleCommand(String input, int count, String command, String[] splitInputs) throws IllegalEmptyException,
            IllegalCommandException, IllegalTaskException, IllegalKeywordException, IllegalIndexException {
        if (command.equalsIgnoreCase("list")) {
            Ui.printList(count,taskList);
        } else if (command.equals(Utils.MARK)) {
            taskList.markItem(splitInputs, count);
        } else if (command.equals(Utils.UNMARK)) {
            taskList.unmarkItem(splitInputs, count);
        } else if (command.equals(Utils.TODO)) {
            count = taskList.addTodo(count, input);
        } else if (command.equals(Utils.DEADLINE)) {
            count = taskList.addDeadline(count, input);
        } else if (command.equals(Utils.EVENT)) {
            count = taskList.addEvent(count, input);
        } else if (command.equals(Utils.DELETE)) {
            count = taskList.deleteItem(count, splitInputs);
        } else if (command.equals(Utils.FIND)){
            taskList.findItem(input);
        }
            else {
            throw new IllegalCommandException("Please enter a valid command");
        }
        return count;
    }

    /**
     * Throws an IllegalTaskException when the index is out of range or
     * when the mark index is not a number
     *
     * @param splitInputs A String[] containing the user input, split by " " delimeter.
     * @param count Number of items in the list
     * @throws IllegalTaskException if index is out of range or when the index is not a number
     */
    public static void validateMark(String[] splitInputs, int count) throws IllegalTaskException {
        try {

            int index = Integer.parseInt(splitInputs[1]) - 1;

            if (index < 0 || index >= count) {
                throw new IllegalTaskException(Warnings.VALID_INDEX_WARNING + count);
            }

        } catch (NumberFormatException e) {
            throw new IllegalTaskException(Warnings.VALID_NUMBER_WARNING + count);
        }

    }

    /**
     * Returns cleaned string of the trimmed description.
     * Commands such as "todo" or "event" are removed.
     *
     * @param input A String containing the user input
     * @return cleaned string of the trimmed description.
     * @throws IllegalEmptyException when the input only contains the command, but no description
     * OR when the description string is empty
     */
    public static String trimString(String input) throws IllegalEmptyException {
        String output = input.trim();

        // Split the input into two parts: the command and the description
        String[] outputSubstrings = output.split(" ", 2);

        // Check if the length is less than 2, meaning no description was provided
        if (outputSubstrings.length < 2 || outputSubstrings[1].trim().isEmpty()) {
            throw new IllegalEmptyException(Warnings.VALID_DESCRIPTION_WARNING);
        }

        // Return the trimmed description
        return outputSubstrings[1].trim();
    }
}

