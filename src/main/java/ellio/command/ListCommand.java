package ellio.command;

import ellio.BotText;
import ellio.EllioExceptions;
import ellio.storage.Storage;
import ellio.task.Task;
import ellio.task.TaskList;
import ellio.task.Todo;
import ellio.ui.Ui;

public class ListCommand extends Command {

    public ListCommand(String inputCommand) {
        super(inputCommand);
    }

    /**
     * Prints the entire list of Tasks to the UI
     * @param tasks TaskList object reference
     * @param ui Ui interface reference
     * @param storage Storage object reference
     */
    public void execute(TaskList tasks, Ui ui, Storage storage)throws EllioExceptions{

        int numberOfTasks = tasks.getNumberTask();

        if (numberOfTasks < 1){
            throw new EllioExceptions.EmptyListException();
        }
        ui.showList();
        for (int i = 0; i < numberOfTasks; i++){
            Task task = tasks.getTask(i);
            System.out.println((i + 1) + "." + task.getTaskInfo());
        }
        ui.showLine();
    }

}
