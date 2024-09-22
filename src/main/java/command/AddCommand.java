package command;

import data.Storage;
import exceptions.IrisException;
import task.Task;
import task.type.Deadline;
import task.type.Event;
import task.TaskList;
import task.type.Todo;

public class AddCommand extends Command {
    private final String command;
    private final String description;

    public AddCommand(String command, String description) {
        this.command = command;
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) throws IrisException {
        Task newTask;
        switch (command) {
        case "todo":
            newTask = new Todo(description);
            break;
        case "deadline":
            newTask = new Deadline(description);
            break;
        default:
            newTask = new Event(description);
        }
        tasks.add(newTask);
        printAddTaskMessage(tasks);
    }

    private static void printAddTaskMessage(TaskList tasks) {
        System.out.println("Got it. I've added this task:\n"
                + tasks.get(tasks.size() - 1)
                + "\nNow you have "
                + tasks.size()
                + " tasks in the list");
    }
}
