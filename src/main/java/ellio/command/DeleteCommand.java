package ellio.command;

import ellio.BotText;
import ellio.storage.Storage;
import ellio.task.Task;
import ellio.task.TaskList;
import ellio.task.Todo;
import ellio.ui.Ui;

import static ellio.Ellio.storage;

public class DeleteCommand extends Command {

    public DeleteCommand(String inputCommand) {
        super(inputCommand);
    }

    /**
     * Removes a task from the list and the save file
     * @param tasks Input by user
     * @param ui Ui interface reference
     * @param storage Reference for Storage functions
     */
    public void execute(TaskList tasks, Ui ui, Storage storage){
        int index = Integer.parseInt(tasks.getTaskIndex(inputCommand));
        if(index > tasks.getNumberTask()){
            throw new IndexOutOfBoundsException();
        }
        Task deletedTask = tasks.getTask(index-1);
        System.out.println(BotText.LINE_BORDER + "Got it. I've removed this task:\n  " + deletedTask.getTaskInfo());
        tasks.removeTask(index-1);
        tasks.decrementNumberTask();
        storage.updateSavedTaskFile();
        System.out.println("Now you have " + tasks.getNumberTask() + " tasks in the list.\n" + BotText.LINE_BORDER);
    }

}
