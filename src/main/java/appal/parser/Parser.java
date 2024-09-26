package appal.parser;

import appal.commands.*;
import appal.exception.AppalException;

import static appal.common.Utils.COMMAND_BYE;
import static appal.common.Utils.COMMAND_LIST;
import static appal.common.Utils.COMMAND_TODO;
import static appal.common.Utils.COMMAND_DEADLINE;
import static appal.common.Utils.COMMAND_EVENT;
import static appal.common.Utils.COMMAND_MARK;
import static appal.common.Utils.COMMAND_UNMARK;
import static appal.common.Utils.COMMAND_DELETE;
import static appal.common.Utils.COMMAND_INDEX;
import static appal.common.Utils.TASK_DETAILS_START_INDEX;
import static appal.common.Utils.MAX_ARGS;
import static appal.common.Utils.SPACE;
import static appal.common.Utils.SLASH;

public class Parser {
    public Command extractCommand(String[] inputDetails, boolean fromUserInput) throws AppalException {
        String commandType = inputDetails[COMMAND_INDEX];
        switch (commandType) {
        case COMMAND_BYE:
            return new ByeCommand();
        case COMMAND_LIST:
            return new ListCommand();
        case COMMAND_MARK:
            return new MarkTaskCommand(inputDetails, true);
        case COMMAND_UNMARK:
            return new MarkTaskCommand(inputDetails, false);
        case COMMAND_TODO:
            return new AddToDoCommand(inputDetails, fromUserInput);
        case COMMAND_DEADLINE:
            return new AddDeadlineCommand(inputDetails, fromUserInput);
        case COMMAND_EVENT:
            return new AddEventCommand(inputDetails, fromUserInput);
        case COMMAND_DELETE:
            return new DeleteCommand(inputDetails);
        default:
            throw new AppalException();
        }
    }

    /**
     * Returns a String array that splits the user input into three respective parameters:
     * Command type, task details, and additional information
     *
     * @param input User input
     * @return String array, with the command type, task details, and additional information
     * in position 0, 1 and 2 (to 3) respectively
     */
    public String[] extractInputDetails(String input) {
        String[] inputDetails = new String[MAX_ARGS];
        String[] words = input.trim().split(SPACE);
        int wordCount = words.length;

        inputDetails[COMMAND_INDEX] = words[COMMAND_INDEX];
        int inputDetailsIndex = TASK_DETAILS_START_INDEX;
        for (int i = 1; i < wordCount; i++) {
            // Continue to next parameter (additional information) when a string with a forward slash is found
            if (words[i].contains(SLASH)) {
                inputDetailsIndex += 1;
                continue;
            }

            // Continue appending information to current parameter
            String currentParameter = inputDetails[inputDetailsIndex];
            if (currentParameter == null) {
                inputDetails[inputDetailsIndex] = words[i];
            } else {
                inputDetails[inputDetailsIndex] += SPACE + words[i];
            }
        }
        return inputDetails;
    }
}
