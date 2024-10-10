package parser;

import commands.Task;
import exceptions.IllegalEmptyException;
import exceptions.IllegalKeywordException;
import exceptions.IllegalTaskException;
import constants.Warnings;


import java.util.ArrayList;

/** Represents methods that parse user input before passing it to other methods. */
public class Parser {

    /**
     * Returns cleaned string of the trimmed description, without the command.
     * Commands such as "todo" or "event" are removed.
     *
     * @param input A String containing the user input.
     * @return a cleaned string of the trimmed description.
     * @throws IllegalEmptyException when the input only contains the command with no description,
     * OR when the description string is empty.
     */
    public static String trimString(String input) throws IllegalEmptyException {
        String trimmedInput = input.trim();

        // Split the input into two parts: the command and the description
        String[] outputSubstrings = trimmedInput.split(" ", 2);

        // Check if the length is less than 2, meaning no description was provided
        if (outputSubstrings.length < 2 || outputSubstrings[1].trim().isEmpty()) {
            throw new IllegalEmptyException(Warnings.VALID_DESCRIPTION_WARNING);
        }

        // Return the trimmed description
        return outputSubstrings[1].trim();
    }

    /**
     * Checks if the index is valid.
     *
     * @param splitInputs A String[] containing the user input, split by " " delimiter.
     * @param items ArrayList that stores all tasks.
     * @throws IllegalTaskException if index is out of range or when the index is not a number.
     * @throws IllegalEmptyException when there is no index written.
     */
    public void validateIndex(String[] splitInputs, ArrayList<Task> items)
            throws IllegalTaskException, IllegalEmptyException {

        try {
            if (splitInputs.length < 2) {
                throw new IllegalEmptyException("Please enter a valid index!");
            }

            int index = Integer.parseInt(splitInputs[1]) - 1;

            //throws an error when index is out of range
            if (index < 0 || index >= items.size()) {
                throw new IllegalTaskException(Warnings.VALID_INDEX_WARNING + items.size());
            }

        } catch (NumberFormatException e) {
            throw new IllegalTaskException(Warnings.VALID_NUMBER_WARNING);
        }

    }

    /**
     * Checks if the task is completed when parsing task string from the txt file.
     * The task is completed when the number in the second column equals to 1.
     *
     * @param storedTaskSubstrings A String[] containing the stored values, split by "|" delimiter.
     * @param task The task object stored in the ArrayList that we want to check the state of completion.
     */
    public void isComplete(String[] storedTaskSubstrings, Task task) {
        int completed = Integer.parseInt(storedTaskSubstrings[1].trim());
        if (completed == 1) {
            task.setDone(true);
        }
    }

    /**
     * Validate user's find string.
     *
     * @param input user input from command line.
     * @throws IllegalEmptyException when there is no find string present.
     */
    public void validateFindString(String input) throws IllegalEmptyException {
        String[] inputSubstrings = input.split(" ");
        if (inputSubstrings.length < 2 || inputSubstrings[1].trim().isEmpty()) {
            throw new IllegalEmptyException(Warnings.VALID_DESCRIPTION_WARNING);
        }
    }

    /**
     * Validates user event input.
     *
     * @param input user's string input without "event" command.
     * @return a String[] of the user's input split by the " " delimiter.
     * @throws IllegalKeywordException when keywords "form" and "to" are missing
     * @throws IllegalEmptyException when one or more of the description, start or end time is missing.
     */
    public String[] validateEvent(String input) throws IllegalKeywordException, IllegalEmptyException {
        if (!input.contains(" from ") || !input.contains(" to ")) {
            throw new IllegalKeywordException(Warnings.VALID_EVENT_KEYWORD_WARNING);
        }

        String[] splitInputs = input.split(" from | to ");

        if (splitInputs.length < 3) {
            throw new IllegalEmptyException(Warnings.INCOMPLETE_EVENT_WARNING);
        }
        return splitInputs;
    }

    /**
     * Validates user deadline input.
     *
     * @param input user deadline input without "deadline" command.
     * @return a String[] of the user's input split by the " " delimiter.
     * @throws IllegalKeywordException when the 'by' keyword is not found in the user's input.
     * @throws IllegalEmptyException when description or deadline parameters is missing.
     */
    public String[] validateDeadline(String input) throws IllegalKeywordException, IllegalEmptyException {
        if (!input.contains(" by ")) {
            throw new IllegalKeywordException(Warnings.VALID_DEADLINE_KEYWORD_WARNING);
        }

        String[] descriptionSubstrings = input.split(" by ", 2);

        if (descriptionSubstrings.length < 2) {
            throw new IllegalEmptyException(Warnings.INCOMPLETE_DEADLINE_WARNING);
        }
        return descriptionSubstrings;
    }

}

