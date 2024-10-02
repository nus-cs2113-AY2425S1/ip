package atom.command;

import atom.storage.Storage;
import atom.tasklist.TaskList;
import atom.ui.Ui;
import atom.exception.EmptyDeadlineException;
import atom.exception.EmptyEventException;
import atom.exception.EmptyTodoException;
import atom.exception.InvalidDeadlineFormatException;
import atom.exception.InvalidEventFormatException;

public class AddCommand extends Command {

    public static final int TODO_START_INDEX = 5;
    public static final int DEADLINE_START_INDEX = 9;
    public static final int DUE_DATE_START_INDEX_OFFSET = 4;
    public static final int EVENT_START_INDEX = 6;
    public static final int START_DATE_START_INDEX_OFFSET = 6;
    public static final int END_DATE_START_INDEX_OFFSET = 4;

    protected String[] userInputSplit;
    protected String fullCommand;
    protected String command;

    public AddCommand(String[] words, String fullCommand, String command) {
        userInputSplit = words;
        this.fullCommand = fullCommand;
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        switch (command) {
        case "todo":
            executeTodoCommand(tasks, ui);
            break;
        case "deadline":
            executeDeadlineCommand(tasks, ui);
            break;
        case "event":
            executeEventCommand(tasks, ui);
            break;
        }
    }

    private void executeTodoCommand(TaskList tasks, Ui ui) {
        try {
            if (userInputSplit.length == 1) {
                throw new EmptyTodoException();
            }

            String todoName = fullCommand.substring(TODO_START_INDEX);
            tasks.addTodoTaskWithMessage(todoName);

        } catch (EmptyTodoException e) {
            ui.showError(e.getMessage());
        }
    }

    private void executeDeadlineCommand(TaskList tasks, Ui ui) {
        try {
            if (userInputSplit.length == 1) {
                throw new EmptyDeadlineException();
            }

            int deadlineEndIndex = fullCommand.indexOf("/by ");
            if (deadlineEndIndex == -1) {
                throw new InvalidDeadlineFormatException();
            }

            String deadlineName = fullCommand.substring(DEADLINE_START_INDEX, deadlineEndIndex);
            String dueDate = fullCommand.substring(deadlineEndIndex + DUE_DATE_START_INDEX_OFFSET);

            if (deadlineName.trim().isEmpty()) {
                ui.showMissingDeadlineNameMessage();
            } else {
                tasks.addDeadlineTaskWithMessage(deadlineName, dueDate);
            }

        } catch (EmptyDeadlineException e) {
            ui.showError(e.getMessage());
        } catch (InvalidDeadlineFormatException e) {
            ui.showError(e.getMessage());
        }
    }

    private void executeEventCommand(TaskList tasks, Ui ui) {
        try {
            if (userInputSplit.length == 1) {
                throw new EmptyEventException();
            }

            int eventEndIndex = fullCommand.indexOf("/from ");
            if (eventEndIndex == -1) {
                throw new InvalidEventFormatException();
            }

            String eventName = fullCommand.substring(EVENT_START_INDEX, eventEndIndex);

            int fromEndIndex = fullCommand.lastIndexOf("/to ");
            if (fromEndIndex == -1) {
                throw new InvalidEventFormatException();
            }

            String startDate = fullCommand.substring(eventEndIndex + START_DATE_START_INDEX_OFFSET, fromEndIndex);
            String endDate = fullCommand.substring(fromEndIndex + END_DATE_START_INDEX_OFFSET);

            if (eventName.trim().isEmpty()) {
                ui.showMissingEventNameMessage();
            } else {
                tasks.addEventTaskWithMessage(eventName, startDate, endDate);
            }

        } catch (EmptyEventException e) {
            ui.showError(e.getMessage());
        } catch (InvalidEventFormatException e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
        public boolean isExit() {
            return false;
        }
}
