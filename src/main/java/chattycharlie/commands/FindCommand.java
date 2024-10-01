package chattycharlie.commands;

import chattycharlie.CharlieExceptions;
import chattycharlie.TaskList;
import chattycharlie.task.Task;
import chattycharlie.userinteractions.Storage;
import chattycharlie.userinteractions.Ui;

import java.time.format.DateTimeFormatter;

public class FindCommand implements Command {
    private String itemToBeFound;

    public FindCommand(String line) {
        this.itemToBeFound = line.substring(5);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CharlieExceptions {
        int count = 1;
        ui.displaySearchList();
        for(int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.getTask(i);
            if(task.getDescription().contains(itemToBeFound)) {
                ui.displayTaskInList(task, count);
                count++;
            }
        }
        if(count == 1) {
            ui.displayError("No task found that contains: " + itemToBeFound );
        }
    }

}
