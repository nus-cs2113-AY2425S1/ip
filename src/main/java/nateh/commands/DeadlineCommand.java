package nateh.commands;

import nateh.Skeleton;
import nateh.classes.Deadlines;
import nateh.classes.Task;
import nateh.storage.TaskEncoder;

import java.io.IOException;
import java.util.ArrayList;

public class DeadlineCommand extends Command {
    private Deadlines deadline;
    @Override
    public void execute(ArrayList<Task> taskList) {
        try {
            taskList.add(deadline);
            TaskEncoder.addTask(taskList.getLast().toString());
            System.out.print(Skeleton.LINE_BREAK);
            System.out.print("added: ");
            taskList.getLast().print();
            System.out.print(Skeleton.LINE_BREAK);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.print((Skeleton.LINE_BREAK));
            System.out.println("OH NO! You seem to have an invalid input!");
            System.out.println("Format: deadline <description> /by <deadline>");
            System.out.print((Skeleton.LINE_BREAK));
        } catch (IOException e) {
            System.out.print((Skeleton.LINE_BREAK));
            System.out.println("Seems like an error occurred");
            System.out.print((Skeleton.LINE_BREAK));
        }

    }
    public DeadlineCommand(Deadlines deadline) {
        this.deadline = deadline;
    }
}
