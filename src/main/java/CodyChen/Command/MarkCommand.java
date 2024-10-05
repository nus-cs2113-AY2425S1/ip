package CodyChen.Command;
import CodyChen.Ui;
import CodyChen.Storage;
import CodyChen.TaskList;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(String userInput) {
        this.index = Integer.parseInt(userInput.substring("mark ".length())) - 1;;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        tasks.markDone(index);
        ui.showTaskMarked(tasks, index);
        storage.save(tasks);
    }
}
