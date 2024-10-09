package commands;

import storage.TaskEncoder;
import tasks.TaskList;
import ui.Skeleton;
import ui.Ui;

import java.io.IOException;


public class MarkCommand extends Command {
    private int index;
    public MarkCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList taskList, Ui ui) throws IOException {
        if (taskList.isEmpty()) {
            throw new NullPointerException();
        }
        taskList.get(index).setDone(true);
        TaskEncoder.markTask(index);
        System.out.print(Skeleton.LINE_BREAK);
        System.out.println("Wow! Great job! :)");
        taskList.get(index).print();
        System.out.print(Skeleton.LINE_BREAK);
    }
}
