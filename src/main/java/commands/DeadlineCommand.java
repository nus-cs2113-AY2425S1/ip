package commands;

import java.util.ArrayList;

import tasks.Deadlines;
import tasks.Task;
import ui.Skeleton;
import ui.Ui;

public class DeadlineCommand extends Command {
    private Deadlines deadline;
    @Override
    public void execute(ArrayList<Task> taskList, Ui ui) {
        try {
            taskList.add(deadline);
            System.out.print(Skeleton.LINE_BREAK);
            System.out.print("added: ");
            taskList.get(taskList.size() - 1).print();
            System.out.print(Skeleton.LINE_BREAK);
        } catch (StringIndexOutOfBoundsException e) {
            ui.invalidTask(deadline);
        }
    }
    public DeadlineCommand(Deadlines deadline) {
        this.deadline = deadline;
    }
}
