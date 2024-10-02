package nateh.commands;

import nateh.Skeleton;
import nateh.classes.Task;
import nateh.classes.Todo;
import nateh.storage.TaskEncoder;

import java.io.IOException;
import java.util.ArrayList;

public class TodoCommand extends Command {
    private Todo todo;
    @Override
    public void execute(ArrayList<Task> taskList) {
        try {
            taskList.add(todo);
            TaskEncoder.addTask(taskList.getLast().toString());
            System.out.print(Skeleton.LINE_BREAK);
            System.out.println("added: " + taskList.getLast().getTask());
            System.out.print(Skeleton.LINE_BREAK);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.print((Skeleton.LINE_BREAK));
            System.out.println("OH NO! You seem to be missing a description");
            System.out.println("Format: todo <description>");
            System.out.print((Skeleton.LINE_BREAK));
        } catch (IOException e) {
            System.out.print((Skeleton.LINE_BREAK));
            System.out.println("Seems like an error occurred");
            System.out.print((Skeleton.LINE_BREAK));
        }
    }
    public TodoCommand(Todo todo) {
        this.todo = todo;
    }
}
