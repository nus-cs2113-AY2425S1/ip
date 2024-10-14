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
 * This command allows the user to specify a task description, start date, and end date for the event.
 */
public class EventCommand extends Command {

    public static final String COMMAND_WORD = "event";
    private static final String EVENT_USAGE = "Usage: event <task description> /from <start date> /to <end date>.";

    private static String description;
    private static LocalDate from;
    private static LocalDate to;

    /**
     * Executes the Event command by validating the input and adding a new Event task to the task manager.
     *
     * @param inputs The input arguments provided by the user.
     * @param taskManager The {@code TaskList} instance managing tasks.
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

    /**
     * Validates the input for creating an Event task.
     *
     * @param inputs The input arguments provided by the user.
     * @throws InvalidInputException If the input format is invalid.
     */
    private static void validateEventInput(String[] inputs) throws InvalidInputException {
        checkForDescription(inputs);
        checkForFrom(inputs);
        checkForTo(inputs);
        String[] splitInput = inputs[1].split(" /from ");
        checkForStartDate(splitInput);
        String[] eventDetails = splitInput[1].split(" /to ");
        checkForEndDate(eventDetails);
        description = splitInput[0];
        checkForValidDate(eventDetails);
    }

    /**
     * Validates the start and end dates for the Event task are in the correct {@code YYYY-MM-DD} format.
     *
     * @param eventDetails The input array split by "/to".
     * @throws InvalidInputException If the date format is invalid.
     */
    private static void checkForValidDate(String[] eventDetails) throws InvalidInputException {
        try {
            from = LocalDate.parse(eventDetails[0]);
            to = LocalDate.parse(eventDetails[1]);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("Invalid date. Use YYYY-MM-DD format.");
        }
    }

    /**
     * Checks if both the end date are provided in the input.
     *
     * @param eventDetails The input array split by "/to".
     * @throws InvalidInputException If the start or end date is missing.
     */
    private static void checkForEndDate(String[] eventDetails) throws InvalidInputException {
        if (eventDetails.length != 2) {
            throw new InvalidInputException("Description/start/end not entered.");
        }
    }

    /**
     * Checks if the start date is provided in the input.
     *
     * @param splitInput The input array split by "/from".
     * @throws InvalidInputException If the start date is missing.
     */
    private static void checkForStartDate(String[] splitInput) throws InvalidInputException {
        if (splitInput.length != 2) {
            throw new InvalidInputException("Description/start/end not entered.");
        }
    }

    /**
     * Checks if the "/to" keyword is present in the user input to indicate the end date.
     *
     * @param inputs The input arguments provided by the user.
     * @throws InvalidInputException If the "/to" keyword is missing.
     */
    private static void checkForTo(String[] inputs) throws InvalidInputException {
        if (!inputs[1].contains("/to")) {
            throw new InvalidInputException("/to not entered.");
        }
    }

    /**
     * Checks if the "/from" keyword is present in the user input to indicate the start date.
     *
     * @param inputs The input arguments provided by the user.
     * @throws InvalidInputException If the "/from" keyword is missing.
     */
    private static void checkForFrom(String[] inputs) throws InvalidInputException {
        if (!inputs[1].contains("/from")) {
            throw new InvalidInputException("/from not entered.");
        }
    }

    /**
     * Checks if the task description is provided in the input.
     *
     * @param inputs The input arguments provided by the user.
     * @throws InvalidInputException If the task description is missing.
     */
    private static void checkForDescription(String[] inputs) throws InvalidInputException {
        if (inputs.length != 2) {
            throw new InvalidInputException("No description entered.");
        }
    }
}
