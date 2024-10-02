package Commands;

import exceptions.InvalidCommandException;
import taskmanager.Storage;
import tasks.Task;
import tasks.Todo;

/**
 * The AddTodoCommand class handles the creation of a todo task based on the user's input.
 * It parses the task description from the input, validates it, and adds the task to storage if valid.
 */

public class AddTodoCommand extends AddCommand {

    public AddTodoCommand(String userInput) {
        super(userInput);
    }

    /**
     * Executes the add todo command by parsing the user input to create a new Todo task.
     * The task description is extracted from the input and validated. If valid, the task
     * is inserted into storage. If the task description is empty or invalid, an exception is thrown.
     *
     * @param storage The storage object that manages the task list.
     * @throws InvalidCommandException If the task description is invalid or empty.
     */

    @Override
    public void execute(Storage storage) throws InvalidCommandException {
        if (userInput.trim().length() <= 4) {
            throw new InvalidCommandException("The description of the todo task cannot be empty");
        }
        String taskContent = userInput.substring(5).trim();
        Task task = new Todo(taskContent);
        storage.storageInsert(task);
        storage.storageList();
    }
}

