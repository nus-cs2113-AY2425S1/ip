package commands;

import java.util.ArrayList;

import tasks.Task;
import ui.Skeleton;
import ui.Ui;

public class DeleteCommand extends Command {
    private int index;
    @Override
    public void execute(ArrayList<Task> taskList, Ui ui) {
        try {
            Task temp = taskList.get(index);
            System.out.print(Skeleton.LINE_BREAK);
            System.out.println("Okay. I've deleted the task:");
            temp.print();
            System.out.printf("Now you have %d tasks\n", taskList.size());
            System.out.print(Skeleton.LINE_BREAK);
            taskList.remove(index);
        } catch (IndexOutOfBoundsException e) {
            System.out.print(Skeleton.LINE_BREAK);
            System.out.println("Hmm seems like you tried to delete a task that doesn't exist");
            System.out.print(Skeleton.LINE_BREAK);
        }
    }
    public DeleteCommand(int index) {
        this.index = index;
    }
}
