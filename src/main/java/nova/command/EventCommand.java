package nova.command;

import nova.TaskList;
import nova.Ui;
import nova.exception.InsufficientSpaceException;
import nova.exception.InvalidInputException;
import nova.task.Event;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a command to create an Event task.
 * This command allows the user to specify a task description, a start time, and an end time.
 */
public class EventCommand extends Command {

    public static final String COMMAND_WORD = "event";
    private static final String EVENT_USAGE = "Usage: event <task description> /from <start date> /to <end date>.";

    private static String description;
    private static LocalDate from;
    private static LocalDate to;


    /**
     * Executes the Event command by validating input, checking space,
     * and adding a new Event task to the task manager.
     *
     * @param inputs The input arguments provided by the user.
     * @param taskManager The TaskList instance managing tasks.
     */
    public static void execute(String[] inputs, TaskList taskManager) {
        try {
            validateEventInput(inputs);
            taskManager.checkSpace();
            taskManager.addTask(new Event(description, from, to));
        } catch (InvalidInputException e) {
            Ui.displayInvalidInputMessage(e.getMessage(), EVENT_USAGE);
        } catch (InsufficientSpaceException e) {
            Ui.displayInvalidInputMessage(e.getMessage());
        }
    }

    private static void validateEventInput(String[] inputs) {
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
        description = splitInput[0];
        try {
            from = LocalDate.parse(eventDetails[0]);
            to = LocalDate.parse(eventDetails[1]);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException(e.getMessage());
        }
    }
}
