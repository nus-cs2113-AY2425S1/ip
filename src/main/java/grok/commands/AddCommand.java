package grok.commands;

import grok.tasks.Task;
import grok.tasks.Todo;
import grok.tasks.Deadline;
import grok.tasks.Event;
import grok.tasks.TaskList;
import grok.storage.Storage;
import grok.ui.Ui;

public class AddCommand extends Command {
    private final String taskType;
    private final String taskDetails;

    public AddCommand(String taskType, String taskDetails) {
        this.taskType = taskType;
        this.taskDetails = taskDetails;
    }

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

    @Override
    public boolean isExit() {
        return false;
    }
}
