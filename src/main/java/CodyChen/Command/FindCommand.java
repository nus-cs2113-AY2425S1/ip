package CodyChen.Command;
import CodyChen.*;

public class FindCommand extends Command {
    private String toFind;

    public FindCommand(String toFind) {
        this.toFind = toFind.substring("find ".length());
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.toFind(tasks, toFind);
    }
}
