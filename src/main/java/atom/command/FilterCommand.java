package atom.command;

import atom.exception.MissingDateException;
import atom.parser.DateTimeParser;
import atom.storage.Storage;
import atom.task.Task;
import atom.tasklist.TaskList;
import atom.ui.Ui;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class FilterCommand extends Command{

    private static final int DATE_START_INDEX = 7;

    private String[] userInputSplit;
    private String fullCommand;

    public FilterCommand(String[] words, String fullCommand) {
        userInputSplit = words;
        this.fullCommand = fullCommand;
    }

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

    @Override
    public boolean isExit() {
        return false;
    }
}
