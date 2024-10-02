package commands;

import tasks.TaskList;
import ui.Skeleton;
import ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui) {
        System.out.print(Skeleton.LINE_BREAK);
        for (int i = 0; i < taskList.size(); i++) {
            System.out.print(i + 1 + ". ");
            taskList.get(i).print();
        }
        System.out.print(Skeleton.LINE_BREAK);
    }
}
