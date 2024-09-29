package ellio.command;

import ellio.storage.Storage;
import ellio.task.Task;
import ellio.task.TaskList;
import ellio.ui.Ui;

public class FindCommand extends Command {

    public FindCommand(String inputCommand) {
        super(inputCommand);
    }

    /**
     * Execute a search on current list and prints tasks on match
     * Iterates through the List and checks the task one by one
     * If there is a keyword match with the description, prints the task to output.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String[] keyword = inputCommand.split(" ", 2);
        int findIndex = 1;
        ui.showLineWithoutNewline();
        for (int i = 0; i < tasks.getNumberTask(); i++) {
            String currentListDescription = tasks.getTask(i).getTaskInfo();
            if(currentListDescription.contains(keyword[1])) {
                if(findIndex == 1){
                    ui.showMatch();
                }
                System.out.println((findIndex) + "." + currentListDescription);
                findIndex++;
            }
        }
        if(findIndex == 1) {
            ui.showFailedSearch();
        }
        ui.showLine();
    }
}
