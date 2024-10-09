package atom.command;

import atom.parser.DateTimeParser;
import atom.storage.Storage;
import atom.tasklist.TaskList;
import atom.ui.Ui;
import atom.exception.EmptyDeadlineException;
import atom.exception.EmptyEventException;
import atom.exception.EmptyTodoException;
import atom.exception.InvalidDeadlineFormatException;
import atom.exception.InvalidEventFormatException;

import java.time.format.DateTimeParseException;

/**
 * Represents a command that adds different types of tasks to the task list.
 */
public class AddCommand extends Command {

    private static final int TODO_START_INDEX = 5;
    private static final int DEADLINE_START_INDEX = 9;
    private static final int DUE_DATE_START_INDEX_OFFSET = 4;
    private static final int EVENT_START_INDEX = 6;
    private static final int START_DATE_START_INDEX_OFFSET = 6;
    private static final int END_DATE_START_INDEX_OFFSET = 4;

    private String[] userInputSplit;
    private String fullCommand;
    private String command;

    public AddCommand(String[] words, String fullCommand, String command) {
        userInputSplit = words;
        this.fullCommand = fullCommand;
        this.command = command;
    }

    /**
     * Adds a <code>todo</code>, <code>deadline</code>, or <code>event</code>
     * task to the task list depending on the task type specified in the command.
     */
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

    /**
     * Adds a <code>todo</code> task to the task list.
     * <p>
     * If the user command only contains the <code>todo</code> keyword,
     * an <code>EmptyTodoException</code> is thrown and caught in the method.
     */
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

    /**
     * Adds a <code>deadline</code> task to the task list.
     * <p>
     * If the user command only contains the <code>deadline</code> keyword,
     * an <code>EmptyDeadlineException</code> is thrown and caught in the method.
     * <p>
     * If the <code>deadline</code> command format is invalid, an
     * <code>InvalidDeadlineFormatException</code> is thrown and caught in the method.
     * <p>
     * If the <code>deadline</code> command format is valid but the date time format is invalid,
     * the date time related exceptions will be caught in the method.
     */
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
                if (DateTimeParser.isValidDateTime(dueDate, ui)) {
                    String parsedDateTime = DateTimeParser.parseDateTime(dueDate.trim());
                    tasks.addDeadlineTaskWithMessage(deadlineName, parsedDateTime);
                }
            }
        } catch (EmptyDeadlineException e) {
            ui.showError(e.getMessage());
        } catch (InvalidDeadlineFormatException e) {
            ui.showError(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showInvalidDateTimeFormatMessage();
        } catch (NumberFormatException e) {
            ui.showInvalidDateTimeParamsMessage();
        } catch (DateTimeParseException e) {
            ui.showDateTimeParseErrorMessage();
        }
    }

    /**
     * Adds an <code>event</code> task to the task list.
     * <p>
     * If the user command only contains the <code>event</code> keyword,
     * an <code>EmptyEventException</code> is thrown and caught in the method.
     * <p>
     * If the <code>event</code> command format is invalid, an
     * <code>InvalidEventFormatException</code> is thrown and caught in the method.
     * <p>
     * If the <code>event</code> command format is valid but the date time format is invalid,
     * the date time related exceptions will be caught in the method.
     */
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
                if (DateTimeParser.isValidDateTime(startDate, ui) &&
                        DateTimeParser.isValidDateTime(endDate, ui)) {
                    String parsedStartDateTime = DateTimeParser.parseDateTime(startDate.trim());
                    String parsedEndDateTime = DateTimeParser.parseDateTime(endDate.trim());
                    tasks.addEventTaskWithMessage(eventName, parsedStartDateTime, parsedEndDateTime);
                }
            }
        } catch (EmptyEventException e) {
            ui.showError(e.getMessage());
        } catch (InvalidEventFormatException e) {
            ui.showError(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showInvalidDateTimeFormatMessage();
        } catch (NumberFormatException e) {
            ui.showInvalidDateTimeParamsMessage();
        } catch (DateTimeParseException e) {
            ui.showDateTimeParseErrorMessage();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
        public boolean isExit() {
            return false;
        }
}
