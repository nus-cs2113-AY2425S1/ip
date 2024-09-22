package nova.command;

import nova.TaskList;
import nova.Ui;
import nova.exception.InsufficientSpaceException;
import nova.exception.InvalidInputException;
import nova.task.Event;

/**
 * Represents a command to create an Event task.
 * This command allows the user to specify a task description, a start time, and an end time.
 */
public class EventCommand extends Command {

    /**
     * The command word for the Event command.
     */
    public static final String COMMAND_WORD = "event";

    /**
     * The usage instructions for the Event command.
     */
    private static final String EVENT_USAGE = "Usage: event <task description> /from <start time> /to <end time>.";

    /**
     * Executes the Event command by validating input, checking space,
     * and adding a new Event task to the task manager.
     *
     * @param inputs     The input arguments provided by the user.
     * @param taskManager The TaskList instance managing tasks.
     */
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

    /**
     * Validates the input for creating an Event task.
     * Ensures that the input has the correct format and contains necessary components.
     *
     * @param inputs The input arguments provided by the user.
     * @return An array containing the task description, start time, and end time.
     * @throws InvalidInputException If the input format is invalid.
     */
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
            throw new InvalidInputException("Description/start/end not entered.");
        }
        String[] eventDetails = splitInput[1].split(" /to ");
        if (eventDetails.length != 2) {
            throw new InvalidInputException("Description/start/end not entered.");
        }
        return new String[]{splitInput[0], eventDetails[0], eventDetails[1]};
    }
}
