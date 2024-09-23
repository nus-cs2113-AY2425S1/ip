public class DeleteCommand extends Command{
    private int index;

    public DeleteCommand(String userInput){
        index = Integer.parseInt(userInput.substring("delete ".length())) - 1;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage){
        ui.showTaskDeleted(tasks, index);
        tasks.delTask(index);
        storage.save(tasks);
    }
}
