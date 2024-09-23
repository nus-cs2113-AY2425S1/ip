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

public class AddDeadlineCommand extends Command {
    private String userInput;
    private boolean fromUserInput;
    private DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(INPUT_DATE_FORMAT);

    public AddDeadlineCommand(String userInput, boolean fromUserInput) {
        super(DEADLINE_COMMAND);
        this.userInput = userInput;
        this.fromUserInput = fromUserInput;
    }

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
