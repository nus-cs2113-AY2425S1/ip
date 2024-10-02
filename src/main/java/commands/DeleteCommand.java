package commands;

import tasks.Task;
import tasks.TaskList;
import ui.Skeleton;
import ui.Ui;

public class DeleteCommand extends Command {
    private int index;
    public DeleteCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList taskList, Ui ui) {
        try {
            Task temp = taskList.get(index);
            System.out.print(Skeleton.LINE_BREAK);
            System.out.println("Okay. I've deleted the task:");
            temp.print();
            System.out.printf("Now you have %d tasks\n", taskList.size());
            System.out.print(Skeleton.LINE_BREAK);
            taskList.remove(index);
        } catch (IndexOutOfBoundsException e) {
            ui.printDeleteError();
        }
    }
}
