package command;

import model.Task;
import model.Todo;
import model.Deadline;
import model.Event;
import exception.MondayException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class AddCommand extends Command {
    private String input;

    public AddCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MondayException {
        Task task = null;

        if (input.startsWith("todo ")) {
            String description = input.substring(5).trim();
            if (description.isEmpty()) {
                throw new MondayException("    The description of a todo cannot be empty.");
            }
            task = new Todo(description, false); // isDone = false
        } else if (input.startsWith("deadline ")) {
            String[] parts = input.split(" /by "); // Split based on "/by"
            if (parts.length != 2) { // Check if the split resulted in exactly 2 parts
                throw new MondayException("    Invalid deadline format. Please use: deadline <description> /by <time>");
            }
            String description = parts[0].substring(8).trim(); // Get the description
            String by = parts[1].trim(); // Get the time
            if (description.isEmpty() || by.isEmpty()) {
                throw new MondayException("    The description or deadline time cannot be empty.");
            }
            task = new Deadline(description, false, by); // isDone = false
        } else if (input.startsWith("event ")) {
            if (input.contains(" /from ") && input.contains(" /to ")) {
                String[] parts = input.substring(6).split(" /from | /to ");
                String description = parts[0].trim();
                String from = parts[1].trim();
                String to = parts[2].trim();
                if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                    throw new MondayException("    The description or event time cannot be empty.");
                }
                task = new Event(description, false, from, to); // isDone = false
            } else {
                throw new MondayException("    Invalid event format. Please use: event <description> /from <start time> /to <end time>");
            }
        } else {
            throw new MondayException("    Sorry, I don't understand the command. "
                    + "Please use keywords like: 'todo', 'deadline', or 'event'. "
                    + "For example, 'todo <description>' or 'deadline <description> /by <time>'.");
        }

        tasks.add(task);
        ui.showTaskAdded(task, tasks.size());
        storage.save(tasks.getTasks());
    }
}
