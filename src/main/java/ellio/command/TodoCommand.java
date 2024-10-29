package ellio.command;

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
     * Prints an acknowledge message on successful operation and saves it to file
     * @param tasks Input by user
     * @param ui Ui interface reference
     * @param storage Reference for Storage functions
     */
    public void execute(TaskList tasks, Ui ui, Storage storage){
        String description = inputCommand.replace("todo ", "");
        Todo newTodo = new Todo(description, "0");
        tasks.addTask(newTodo);
        tasks.addNumberTask();
        ui.showAddTaskMessage(newTodo.getTaskInfo(),tasks.getNumberTask());
        storage.saveNewTask(newTodo.getSaveFileTask());
    }
}
