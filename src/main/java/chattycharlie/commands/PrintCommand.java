package chattycharlie.commands;
import chattycharlie.CharlieExceptions;
import chattycharlie.TaskList;
import chattycharlie.task.Deadline;
import chattycharlie.task.Event;
import chattycharlie.task.Task;
import chattycharlie.userinteractions.Storage;
import chattycharlie.userinteractions.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the command to print tasks based on a specific date.
 * This command searches the task list for any tasks that match the provided date.
 */
public class PrintCommand implements Command{
    private LocalDate time;

    /**
     * Constructs a <code>PrintCommand</code> using the provided input line.
     * Parses the input to extract the date to filter tasks by.
     *
     * @param line the input line containing the command and the date to filter tasks.
     * @throws DateTimeParseException if the provided date is not in the correct format.
     */
    public PrintCommand(String line) {
        String timeText = line.substring(6).trim();
        this.time = LocalDate.parse(timeText, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Executes the <code>PrintCommand</code> by searching for and displaying tasks that
     * match the specified date. It looks for deadlines and events that match the given date.
     *
     * @param taskList the list of tasks to search.
     * @param ui the user interface for displaying the search results.
     * @param storage the storage system (not used in this command).
     * @throws CharlieExceptions if an error occurs during the command execution.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CharlieExceptions {
        int count = 1;
        ui.displaySearchList();
        for(int i = 0; i < taskList.getSize(); i++ ) {
            Task task = taskList.getTask(i);
            CommandType command = task.getType();

            switch (command) {
            case DEADLINE:
                Deadline deadlineTask = (Deadline) task;
                if(time.equals(deadlineTask.getBy())) {
                    ui.displayTaskInList(deadlineTask, count);
                    count++;
                }
                break;
            case EVENT:
                Event eventTask = (Event) task;
                if(time.equals(eventTask.getStartDate())) {
                    ui.displayTaskInList(eventTask, count);
                    count++;
                }
                break;
            default:
                break;
            }
        }
        if(count == 1) {
            ui.displayError("No task found for date: " + time.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        }
    }
}
