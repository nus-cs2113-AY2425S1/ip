package commands;

import exception.InvalidDateFormatException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static constants.Command.SHOW_COMMAND;
import static constants.Regex.INPUT_DATE_FORMAT;

/**
 * Represents a command that displays tasks for a specific date in Bento.
 * This class extends {@link Command} and handles the execution of the show command,
 * including parsing the date input and listing tasks associated with that date.
 */
public class ShowCommand extends Command {
    private String dateOfInterestString;
    private final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(INPUT_DATE_FORMAT);

    /**
     * Constructs a ShowCommand with the user's input date.
     *
     * @param userInput The input string containing the date of interest.
     */
    public ShowCommand(String userInput) {
        super(SHOW_COMMAND);
        this.dateOfInterestString = userInput;
    }

    /**
     * Executes the show command, parsing the date and displaying tasks for that date.
     *
     * @param tasks The current task list.
     * @param ui The user interface to interact with the user.
     * @param storage The storage object to handle file operations.
     * @throws InvalidDateFormatException If the input date format is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidDateFormatException {
        try {
            dateOfInterestString = parser.removeShowPrefix(dateOfInterestString);
            LocalDate dateOfInterest = LocalDate.parse(dateOfInterestString, inputFormatter);
            ui.listTasksOfInterest(dateOfInterest, tasks);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException();
        }
    }
}
