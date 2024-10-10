package commands;

import task.TaskList;
import task.Task;
import task.ToDo;
import task.Deadline;
import task.Event;
import UI.Ui;
import storage.Storage;
import exception.LiaException;

/**
 * Command to add a task.
 */
public class AddCommand extends Command {
    private final String taskDetails;

    public AddCommand(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LiaException {
        // Add logic to parse the task details and create the corresponding task
        Task newTask;

        if (taskDetails.startsWith("todo")) {
            newTask = new ToDo(taskDetails.substring(5).trim());
        } else if (taskDetails.startsWith("deadline")) {
            String[] details = taskDetails.split(" /by ");
            newTask = new Deadline(details[0].substring(9).trim(), details[1].trim());
        } else if (taskDetails.startsWith("event")) {
            String[] details = taskDetails.split(" /from ");
            String[] timeDetails = details[1].split(" /to ");
            newTask = new Event(details[0].substring(6).trim(), timeDetails[0].trim(), timeDetails[1].trim());
        } else {
            throw new LiaException("Invalid task type.");
        }

        tasks.add(newTask);
        ui.addTaskAndPrint(newTask, tasks.size());
    }
}
