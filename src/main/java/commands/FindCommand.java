package commands;

import exception.NoTaskFoundException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

import static constants.Command.FIND_COMMAND;


/**
 * Represents a command that finds and displays tasks containing a specified search phrase in Bento.
 * This class extends {@link Command} and handles the execution of the find command,
 * which filters tasks based on user input and displays matching tasks.
 */
public class FindCommand extends Command {
    private String userInput;

    /**
     * Constructs a FindCommand with the user's input for the search phrase.
     *
     * @param userInput The input string indicating the keyword to search for.
     */
    public FindCommand(String userInput) {
        super(FIND_COMMAND);
        this.userInput = userInput;
    }

    /**
     * Executes the find command, searching for tasks that contain the specified search phrase
     * and displaying it to the user.
     *
     * @param tasks The current task list.
     * @param ui The user interface to interact with the user.
     * @param storage The storage object to handle file operations.
     * @throws NoTaskFoundException If no tasks match the search criteria.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NoTaskFoundException {
        userInput = parser.removeFindPrefix(userInput);
        TaskList tasksOfInterest = new TaskList();
        tasks.getTasks().stream()
                .filter((t) -> t.getTaskName().contains(userInput)).forEach(tasksOfInterest::addTask);
        if (tasksOfInterest.getTaskCount() == 0) {
            throw new NoTaskFoundException();
        }
        ui.listTasksOfInterest(tasksOfInterest);
    }
}
