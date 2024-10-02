package commands;

import java.util.ArrayList;

import tasks.Task;
import ui.Skeleton;
import ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(ArrayList<Task> taskList, Ui ui) {
        System.out.print(Skeleton.LINE_BREAK);
        for (int i = 0; i < taskList.size(); i++) {
            System.out.print(i + 1 + ". ");
            taskList.get(i).print();
        }
        System.out.print(Skeleton.LINE_BREAK);
    }
}
