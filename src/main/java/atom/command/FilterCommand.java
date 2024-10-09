package atom.command;

import atom.exception.MissingDateException;
import atom.parser.DateTimeParser;
import atom.storage.Storage;
import atom.task.Task;
import atom.tasklist.TaskList;
import atom.ui.Ui;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Represents a command that filters tasks in task list by date.
 */
public class FilterCommand extends Command{

    private static final int DATE_START_INDEX = 7;

    private String[] userInputSplit;
    private String fullCommand;

    public FilterCommand(String[] words, String fullCommand) {
        userInputSplit = words;
        this.fullCommand = fullCommand;
    }

    /**
     * Filters tasks in task list by date and prints out the resultant task list.
     * <p>
     * If the user command only contains the <code>filter</code> keyword,
     * a <code>MissingDateException</code> is thrown and caught in the method.
     * <p>
     * If the date or date format is invalid, the date related exceptions are caught in the method.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (userInputSplit.length == 1) {
                throw new MissingDateException();
            }
            //filter date
            String dateToFilterBy = fullCommand.substring(DATE_START_INDEX);
            if (DateTimeParser.isValidDate(dateToFilterBy, ui)) {
                String parsedDateToFilterBy = DateTimeParser.parseDate(dateToFilterBy.trim());
                ArrayList<Task> tasksFilteredByDateList = tasks.filterTasksByDate(parsedDateToFilterBy);
                ui.printTasksFilteredByDateList(tasksFilteredByDateList, parsedDateToFilterBy);
            }
        } catch (MissingDateException e) {
            ui.showError(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showInvalidDateFormatMessage();
        } catch (NumberFormatException e) {
            ui.showInvalidDateParamsMessage();
        } catch (DateTimeParseException e) {
            ui.showDateParseErrorMessage();
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
