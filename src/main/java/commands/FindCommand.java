package commands;

import exception.NoTaskFoundException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;


import static constants.Command.FIND_COMMAND;

public class FindCommand extends Command {
    private String userInput;

    public FindCommand(String userInput) {
        super(FIND_COMMAND);
        this.userInput = userInput;
    }

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
