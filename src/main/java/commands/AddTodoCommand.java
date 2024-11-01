package commands;

import exception.InvalidToDoException;
import exception.SaveFileErrorException;
import storage.Storage;
import tasks.TaskList;
import tasks.ToDo;
import ui.Ui;

import static constants.Command.TODO_COMMAND;

/**
 * Represents a command that adds a ToDo to Bento.
 * This class extends {@link Command} and handles the execution of the add ToDo command,
 * which creates a new ToDo based on user input.
 */
public class AddTodoCommand extends Command {
    private String userInput;
    private final boolean fromUserInput;

    /**
     * Constructs an AddTodoCommand with the user's input for the ToDo and a flag indicating
     * if the input is from the user.
     *
     * @param userInput The input string for the To-Do task.
     * @param fromUserInput Indicates if the input was provided by the user.
     */
    public AddTodoCommand(String userInput, boolean fromUserInput) {
        super(TODO_COMMAND);
        this.userInput = userInput;
        this.fromUserInput = fromUserInput;
    }

    /**
     * Executes the add ToDo command, creating a new ToDo task and saving it to the task list.
     *
     * @param tasks The current task list.
     * @param ui The user interface to interact with the user.
     * @param storage The storage object to handle file operations.
     * @throws InvalidToDoException If the To-Do input is invalid (e.g., empty).
     * @throws SaveFileErrorException If an error occurs while saving the task list.
     */
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
