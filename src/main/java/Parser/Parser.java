package Parser;

import constants.Utils;
import exceptions.*;
import Ui.*;
import constants.Warnings;
import TaskList.*;

public class Parser {

    private final TaskList taskList;

    public Parser() {
        taskList = new TaskList();
    }

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
        } else {
            throw new IllegalCommandException("Please enter a valid command");
        }
        return count;
    }

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

