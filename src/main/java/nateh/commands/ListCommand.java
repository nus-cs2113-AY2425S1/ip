package nateh.commands;

import nateh.Skeleton;
import nateh.classes.Task;

import java.util.ArrayList;

public class ListCommand extends Command{
    @Override
    public void execute(ArrayList<Task> taskList) {
        System.out.print(Skeleton.LINE_BREAK);
        for (int i = 0; i < taskList.size(); i++) {
            System.out.print(i + 1 + ". ");
            taskList.get(i).print();
        }
        System.out.print(Skeleton.LINE_BREAK);
    }
    public ListCommand() {
    }
}
