package commands;

import exception.InvalidIndexException;
import exception.InvalidToDoException;
import exception.MissingTaskException;
import exception.SaveFileErrorException;
import storage.Storage;
import tasks.TaskList;
import tasks.ToDo;
import ui.Ui;

import static constants.Command.TODO_COMMAND;

public class AddTodoCommand extends Command {
    private String userInput;
    private boolean fromUserInput;

    public AddTodoCommand(String userInput, boolean fromUserInput) {
        super(TODO_COMMAND);
        this.userInput = userInput;
        this.fromUserInput = fromUserInput;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidToDoException, SaveFileErrorException {
        userInput = parser.getTodo(userInput);

        if (userInput.isEmpty()) {
            throw new InvalidToDoException();
        }

        ToDo toAdd = new ToDo(userInput);
        tasks.addTask(toAdd);

        if (fromUserInput) {
            ui.printAddTaskSuccessMessage(toAdd.toString(), tasks);
        }

        saveTask(storage, tasks, ui);
    }
}
