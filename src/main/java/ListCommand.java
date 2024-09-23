public class ListCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        if(tasks.size() < 0) {
            ui.listEmpty();
        } else {
            ui.listPopulated(tasks);
        }
    }
}
