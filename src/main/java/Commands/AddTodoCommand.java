package Commands;

import exceptions.InvalidCommandException;
import taskmanager.Storage;
import tasks.Task;
import tasks.Todo;

public class AddTodoCommand extends AddCommand {

    public AddTodoCommand(String userInput) {
        super(userInput);
    }

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

