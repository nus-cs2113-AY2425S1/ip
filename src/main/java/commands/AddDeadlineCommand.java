package commands;

import exception.InvalidDeadlineException;
import exception.InvalidIndexException;
import exception.InvalidToDoException;
import exception.MissingTaskException;
import exception.SaveFileErrorException;
import storage.Storage;
import tasks.Deadline;
import tasks.TaskList;
import ui.Ui;

import static constants.Command.DEADLINE_COMMAND;
import static constants.Regex.BY_PREFIX;

public class AddDeadlineCommand extends Command {
    private String userInput;
    private boolean fromUserInput;

    public AddDeadlineCommand(String userInput, boolean fromUserInput) {
        super(DEADLINE_COMMAND);
        this.userInput = userInput;
        this.fromUserInput = fromUserInput;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidDeadlineException, SaveFileErrorException {
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

        Deadline toAdd = new Deadline(deadlineName, deadlineBy);
        tasks.addTask(toAdd);

        if (fromUserInput) {
            ui.printAddTaskSuccessMessage(toAdd.toString(), tasks);
        }

        saveTask(storage, tasks, ui);
    }
}
