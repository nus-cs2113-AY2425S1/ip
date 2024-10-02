package commands;

import constants.Skeleton;
import tasks.Task;
import storage.TaskEncoder;

import java.io.IOException;
import java.util.ArrayList;

public class DeleteCommand extends Command {
    private int index;
    @Override
    public void execute(ArrayList<Task> taskList) {
        try {
            Task temp = taskList.get(index);
            System.out.print(Skeleton.LINE_BREAK);
            System.out.println("Okay. I've deleted the task:");
            temp.print();
            System.out.printf("Now you have %d tasks\n", taskList.size());
            System.out.print(Skeleton.LINE_BREAK);
            taskList.remove(index);
            TaskEncoder.deleteTask(index);
        } catch (IndexOutOfBoundsException e) {
            System.out.print(Skeleton.LINE_BREAK);
            System.out.println("Hmm seems like you tried to delete a task that doesn't exist");
            System.out.print(Skeleton.LINE_BREAK);
        } catch (IOException e) {
            System.out.print((Skeleton.LINE_BREAK));
            System.out.println("Seems like an error occurred");
            System.out.print((Skeleton.LINE_BREAK));
        }
    }
    public DeleteCommand(int index) {
        this.index = index;
    }
}
