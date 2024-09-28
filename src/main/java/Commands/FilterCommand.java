package Commands;

import AlyBot.AlyException;
import AlyBot.Storage;
import AlyBot.TaskList;
import AlyBot.Ui;

import Task.Task;
import Task.Deadline;
import Task.Event;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a command to filter tasks by a specific date.
 */
public class FilterCommand extends Command {

    private TaskList taskList;

    /**
     * Constructs a FilterCommand with the specified instruction.
     *
     * @param instruction The date instruction to filter tasks by.
     */
    public FilterCommand(String instruction) {
        super(instruction);
        this.taskList = new TaskList();
    }

    /**
     * Executes the filter command by filtering tasks in the given task list
     * based on the specified date.
     *
     * @param taskList The list of tasks to filter.
     * @param ui The user interface to display results.
     * @param storage The storage to manage task data.
     * @throws AlyException If the date format is invalid or an error occurs.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AlyException {
        if (!isValidDateFormat(instruction)) {
            throw new AlyException("Wrong date format lah! Use 'yyyy-mm-dd' for crying out loud!");
        }
        LocalDate filterDate = LocalDate.parse(instruction, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String formattedDate = filterDate.format(DateTimeFormatter.ofPattern("MMM-d-yyyy"));
        for (Task task : taskList.getList()) {
            if (!(task instanceof Deadline || task instanceof Event)) {
                continue;
            }

            if (task instanceof Deadline) {
                if (formattedDate.equals(((Deadline) task).getFormattedDueDateTime().substring(0, 11).trim())) {
                    this.taskList.addTask(task);
                }
            } else {
                if (formattedDate.equals(((Event) task).getFormattedStartDateTime().substring(0, 11).trim())
                        || formattedDate.equals(((Event) task).getFormattedEndDateTime().substring(0, 11).trim())) {
                    this.taskList.addTask(task);
                }
            }
        }
        ui.showList(this.taskList);
    }
}