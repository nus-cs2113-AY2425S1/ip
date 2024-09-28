package commands;
import exception.IncompleteCommandException;
import exception.InvalidTaskContentException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;
import task.*;

import static main.Sirius.*;

/**
 * AddCommand is responsible for adding a new task (Todo, Deadline, or Event) based on user input.
 * It processes the input and adds the appropriate task to the task list.
 */
public class AddCommand extends Command {
    private final String userInput;

    public AddCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the AddCommand by parsing the user input and adding the corresponding task to the TaskList.
     * It supports three types of tasks: Todo, Deadline, and Event.
     *
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IncompleteCommandException, InvalidTaskContentException {
        String[] slashCommand = userInput.split(SLASH);
        String commandContent = slashCommand[0]; //commandContent = commandPrefix + taskName
        String commandPrefix = commandContent.split(SPACE)[0];
        String taskName = commandContent.replace(commandPrefix, EMPTY).trim();

        try {
            switch (commandPrefix) {
                case TODO:
                    if (taskName.isEmpty()) {
                        throw new IncompleteCommandException(commandPrefix);
                    } else{
                        tasks.addTask(new Todo(taskName, false));
                    }

                    break;
                case DEADLINE:
                    int indexOfBy = userInput.indexOf("/by");
                    if (taskName.isEmpty()) {
                        throw new IncompleteCommandException(commandPrefix);
                    } else if (indexOfBy == -1) {  // cannot find "/by"
                        throw new InvalidTaskContentException("You should declare '/by' for deadline");
                    } else {
                        String byTime = slashCommand[1].replace("by", EMPTY).trim();
                        tasks.addTask(new Deadline(taskName, false, byTime));
                    }
                    break;
                case EVENT:
                    int indexOfFrom = userInput.indexOf("/from");
                    int indexOfTo = userInput.indexOf("/to");
                    if (taskName.isEmpty()) {
                        throw new IncompleteCommandException(commandPrefix);
                    } else if (indexOfFrom == -1 || indexOfTo == -1) {
                        throw new InvalidTaskContentException("You should declare '/from' and '/to' for event");
                    } else {
                        String fromTime = slashCommand[1].replace("from", EMPTY).trim();
                        String toTime = slashCommand[2].replace("to", EMPTY).trim();
                        tasks.addTask(new Event(taskName, false, fromTime, toTime));
                    }
            }
            ui.showTaskAdded(tasks, tasks.getListSize());
            ui.showCurrentSizeOfList(tasks);
        } catch (InvalidTaskContentException | IncompleteCommandException e) {
            ui.showLine();
            ui.print(e.getMessage());
            ui.showLine();
        }
        storage.saveTaskList(tasks.getList(), ui);
    }
}