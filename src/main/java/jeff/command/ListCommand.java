package jeff.command;

import jeff.helper.Storage;
import jeff.helper.TaskList;
import jeff.helper.Ui;

public class ListCommand extends Command {
    public ListCommand(String firstWord, String line) {
        super(firstWord, line);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        System.out.print("orh hor never finish ur tasks:");
        for(int i = 1; i <= tasks.getCount(); i++){
            System.out.print(System.lineSeparator() + i + "." + tasks.getTask(i-1));
        }
    }
}
