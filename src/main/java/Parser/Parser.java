package Parser;

import commands.Task;
import constants.Utils;
import exceptions.*;
import Ui.Ui;
import constants.Warnings;
import TaskList.TaskList;

import java.util.ArrayList;

public class Parser {

    private final Ui ui;

    public Parser() {
        ui = new Ui();
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

    public void validateMark(String[] splitInputs, ArrayList<Task> items) throws IllegalTaskException, IllegalEmptyException{
        try {

            if (splitInputs.length < 2) {
                throw new IllegalEmptyException("Please enter a valid index!");
            }

            int index = Integer.parseInt(splitInputs[1]) - 1;

            if (index < 0 || index >= items.size()) {
                throw new IllegalTaskException(Warnings.VALID_INDEX_WARNING + items.size());
            }

        } catch (NumberFormatException e) {
            throw new IllegalTaskException(Warnings.VALID_NUMBER_WARNING);
        }

    }

    public void checkComplete(String[] storedTaskSubstrings, Task task) {
        int completed = Integer.parseInt(storedTaskSubstrings[1].trim());
        if (completed == 1) {
            task.setDone(true);
        }
    }
}

