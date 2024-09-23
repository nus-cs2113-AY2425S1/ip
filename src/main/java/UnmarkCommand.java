public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(String userInput) {
        this.index = Integer.parseInt(userInput.substring("unmark ".length())) - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        tasks.markUndone(index);
        ui.showTaskUnmarked(tasks, index);
        //storage.save(tasks);
    }
}
