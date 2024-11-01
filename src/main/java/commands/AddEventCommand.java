package commands;

import exception.InvalidDateFormatException;
import exception.InvalidEventException;
import exception.SaveFileErrorException;
import storage.Storage;
import tasks.Event;
import tasks.TaskList;
import ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static constants.Command.EVENT_COMMAND;
import static constants.Regex.FROM_PREFIX;
import static constants.Regex.INPUT_DATE_FORMAT;
import static constants.Regex.TO_PREFIX;

/**
 * Represents a command that adds an Event task to Bento.
 * This class extends {@link Command} and handles the execution of the add Event command,
 * which creates a new Event based on user input, including its date range.
 */
public class AddEventCommand extends Command {
    private String userInput;
    private final boolean fromUserInput;
    private final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(INPUT_DATE_FORMAT);

    /**
     * Constructs an AddEventCommand with the user's input for the Event and a flag indicating
     * if the input is from the user.
     *
     * @param userInput The input string for the Event task.
     * @param fromUserInput Indicates if the input was provided by the user.
     */
    public AddEventCommand(String userInput, boolean fromUserInput) {
        super(EVENT_COMMAND);
        this.userInput = userInput;
        this.fromUserInput = fromUserInput;
    }

    /**
     * Checks if the 'to' date is after or equal to the 'from' date.
     *
     * @param from The start date of the event.
     * @param to The end date of the event.
     * @return true if the 'to' date is valid; false otherwise.
     */
    public boolean isValidFromAndTo(LocalDate from, LocalDate to) {
        return (to.isAfter(from) || to.isEqual(from));
    }

    /**
     * Executes the add Event command, creating a new Event and saving it to the task list.
     *
     * @param tasks The current task list.
     * @param ui The user interface to interact with the user.
     * @param storage The storage object to handle file operations.
     * @throws InvalidEventException If the Event input is invalid (e.g., missing dates or name).
     * @throws SaveFileErrorException If an error occurs while saving the task list.
     * @throws InvalidDateFormatException If the date format is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidEventException,
            SaveFileErrorException, InvalidDateFormatException {
        userInput = parser.removeEventPrefix(userInput);
        int indexOfFrom = userInput.indexOf(FROM_PREFIX);
        int indexOfTo = userInput.indexOf(TO_PREFIX);

        if (indexOfFrom == -1 || indexOfTo == -1) {
            throw new InvalidEventException();
        }

        String eventName = parser.extractEventName(userInput, indexOfFrom);
        String fromString = parser.extractFromString(userInput, indexOfFrom, indexOfTo);
        String toString = parser.extractToString(userInput, indexOfTo);

        if (eventName.isEmpty() || fromString.isEmpty() || toString.isEmpty()) {
            throw new InvalidEventException();
        }
        try {
            LocalDate fromDate = LocalDate.parse(fromString, inputFormatter);
            LocalDate toDate = LocalDate.parse(toString, inputFormatter);

            if (!isValidFromAndTo(fromDate, toDate)) {
                throw new InvalidEventException();
            }

            Event toAdd = new Event(eventName, fromDate, toDate);
            tasks.addTask(toAdd);

            if (fromUserInput) {
                ui.printAddTaskSuccessMessage(toAdd.toString(), tasks);
            }

            saveTask(storage, tasks, ui);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException();
        }
    }
}
