import java.util.ArrayList;

public class FindCommand extends Command {

    String toFind;

    FindCommand(String toFind) {
        this.toFind = toFind.substring("find ".length());
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.toFind(tasks, toFind);
    }
}
