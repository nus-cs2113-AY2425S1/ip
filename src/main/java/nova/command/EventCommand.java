package nova.command;

import nova.TaskList;
import nova.Ui;
import nova.exception.InsufficientSpaceException;
import nova.exception.InvalidInputException;
import nova.task.Event;

public class EventCommand extends Command {

    public static final String COMMAND_WORD = "event";
    private static final String EVENT_USAGE = "Usage: event <task description> /from <start time> /to <end time>.";

    public static void execute(String[] inputs, TaskList taskManager) {
        String[] validatedInput;
        try {
            validatedInput = validateEventInput(inputs);
            taskManager.checkSpace();
            taskManager.addTask(new Event(validatedInput[0], validatedInput[1], validatedInput[2]));
        } catch (InvalidInputException e) {
            Ui.displayInvalidInputMessage(e.getMessage(), EVENT_USAGE);
        } catch (InsufficientSpaceException e) {
            Ui.displayInvalidInputMessage(e.getMessage());
        }
    }

    protected static String[] validateEventInput(String[] inputs) {
        if (inputs.length != 2) {
            throw new InvalidInputException("No description entered.");
        }
        if (!inputs[1].contains("/from")) {
            throw new InvalidInputException("/from not entered.");
        }
        if (!inputs[1].contains("/to")) {
            throw new InvalidInputException("/to not entered.");
        }
        String[] splitInput = inputs[1].split(" /from ");
        if (splitInput.length != 2) {
            throw new InvalidInputException("description/start/end not entered.");
        }
        String[] eventDetails = splitInput[1].split(" /to ");
        if (eventDetails.length != 2) {
            throw new InvalidInputException("description/start/end not entered.");
        }
        return new String[]{splitInput[0], eventDetails[0], eventDetails[1]};
    }
}
