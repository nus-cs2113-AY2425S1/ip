package grok.commands;

import grok.tasks.Task;
import grok.tasks.Todo;
import grok.tasks.Deadline;
import grok.tasks.Event;
import grok.tasks.TaskList;
import grok.storage.Storage;
import grok.ui.Ui;

/**
 * Represents a command to add a task to the task list.
 * It supports adding To-do, Deadline, and Event tasks.
 */
public class AddCommand extends Command {
    private final String taskType;
    private final String taskDetails;

    /**
     * Constructs an AddCommand with the specified task type and details.
     * @param taskType the type of the task (todo, deadline, or event).
     * @param taskDetails the details of the task.
     */
    public AddCommand(String taskType, String taskDetails) {
        this.taskType = taskType;
        this.taskDetails = taskDetails;
    }

    /**
     * Executes the AddCommand to add a task to the task list.
     *
     * @param tasks the TaskList to add the task to.
     * @param ui the Ui to interact with the user.
     * @param storage the Storage to save the task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask;

        switch (taskType) {
        case "todo":
            if (taskDetails.isEmpty()) {
                ui.showError("Oh no, todo cannot be empty. Do something!");
                return;
            }
            newTask = new Todo(taskDetails);
            break;
        case "deadline":
            String[] deadlineDetails = taskDetails.split(" /by ");
            if (deadlineDetails.length < 2) {
                ui.showError("Invalid deadline command. Please use: deadline <description> /by <date/time>");
                return;
            }
            newTask = new Deadline(deadlineDetails[0], deadlineDetails[1]);
            break;
        case "event":
            String[] eventDetails = taskDetails.split(" /from | /to ");
            if (eventDetails.length < 3) {
                ui.showError("Invalid event command. Please use: event <description> /from <start> /to <end>");
                return;
            }
            newTask = new Event(eventDetails[0], eventDetails[1], eventDetails[2]);
            break;
        default:
            ui.showError("Unknown task type.");
            return;
        }

        tasks.addTask(newTask);
        ui.showLine();
        ui.showMsg("Got it. I've added this task:");
        ui.showMsg(newTask.toString());
        ui.showMsg("Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in the list.");
        ui.showLine();

        try {
            storage.saveTasks(tasks.getTasks());
        } catch (Exception e) {
            ui.showError("Error saving tasks to file.");
        }
    }

    /**
     * Returns whether this command will terminate the program.
     * @return false because AddCommand does not exit the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
