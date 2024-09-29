package ellio.command;

import ellio.BotText;
import ellio.storage.Storage;
import ellio.task.TaskList;
import ellio.task.Todo;
import ellio.ui.Ui;


public class TodoCommand extends Command{

    public TodoCommand(String inputCommand){
        super(inputCommand);
    }

    /**
     * Adds a Todo type Task into the list
     * @param tasks Input by user
     * @param ui Ui interface reference
     * @param storage Reference for Storage functions
     */
    public void execute(TaskList tasks, Ui ui, Storage storage){
        String description = inputCommand.replace("todo ", "");
        Todo newTodo = new Todo(description, "0");
        tasks.addTask(newTodo);
        tasks.addNumberTask();
        System.out.println(BotText.LINE_BORDER + "Got it. I've added this task:\n  " + newTodo.getTaskInfo());
        System.out.println("Now you have " + tasks.getNumberTask() + " tasks in the list.\n" + BotText.LINE_BORDER);
        storage.saveNewTask(newTodo.getSaveFileTask());
    }
}
