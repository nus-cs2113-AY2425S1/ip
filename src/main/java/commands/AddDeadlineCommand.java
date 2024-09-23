package commands;

import exception.InvalidDateFormatException;
import exception.InvalidDeadlineException;
import exception.SaveFileErrorException;
import storage.Storage;
import tasks.Deadline;
import tasks.TaskList;
import ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static constants.Command.DEADLINE_COMMAND;
import static constants.Regex.BY_PREFIX;
import static constants.Regex.INPUT_DATE_FORMAT;

/**
 * Represents a command that adds a Deadline to Bento.
 * This class extends {@link Command} and handles the execution of the add Deadline command,
 * which creates a new Deadline based on user input, including its due date.
 */
public class AddDeadlineCommand extends Command {
    private String userInput;
    private final boolean fromUserInput;
    private final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(INPUT_DATE_FORMAT);

    /**
     * Constructs an AddDeadlineCommand with the user's input for the Deadline and a flag indicating
     * if the input is from the user.
     *
     * @param userInput The input string for the Deadline task.
     * @param fromUserInput Indicates if the input was provided by the user.
     */
    public AddDeadlineCommand(String userInput, boolean fromUserInput) {
        super(DEADLINE_COMMAND);
        this.userInput = userInput;
        this.fromUserInput = fromUserInput;
    }

    /**
     * Executes the add Deadline command, creating a new Deadline and saving it to the task list.
     *
     * @param tasks The current task list.
     * @param ui The user interface to interact with the user.
     * @param storage The storage object to handle file operations.
     * @throws InvalidDeadlineException If the Deadline input is invalid (e.g., missing name or date).
     * @throws SaveFileErrorException If an error occurs while saving the task list.
     * @throws InvalidDateFormatException If the date format is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidDeadlineException, SaveFileErrorException, InvalidDateFormatException {
        userInput = parser.removeDeadlinePrefix(userInput);
        int indexOfByPrefix = userInput.indexOf(BY_PREFIX);

        if (indexOfByPrefix == -1) {
            throw new InvalidDeadlineException();
        }

        String deadlineName = parser.extractDeadlineName(userInput);
        String deadlineBy = parser.extractDeadlineBy(userInput);

        if (deadlineName.isEmpty() || deadlineBy.isEmpty()) {
            throw new InvalidDeadlineException();
        }

        // Parse deadlineBy as a LocalDateTime Object
        try {
            LocalDate deadlineDate = LocalDate.parse(deadlineBy, inputFormatter);
            // Update String representation
            // deadlineBy = date.format(saveFormatter);

            Deadline toAdd = new Deadline(deadlineName, deadlineDate);
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
