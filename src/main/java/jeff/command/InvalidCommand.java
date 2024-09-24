package jeff.command;

import jeff.helper.Storage;
import jeff.helper.TaskList;
import jeff.helper.Ui;

public class InvalidCommand extends Command {
    public InvalidCommand() {
        super();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        System.out.print("eh can you give me one of the inputs i specified above...");
    }
}
