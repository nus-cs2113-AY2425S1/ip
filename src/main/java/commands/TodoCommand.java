package commands;


import constants.Skeleton;
import tasks.Task;
import tasks.Todo;
import storage.TaskEncoder;

import java.io.IOException;
import java.util.ArrayList;

public class TodoCommand extends Command {
    private Todo todo;
    @Override
    public void execute(ArrayList<Task> taskList) {
        try {
            taskList.add(todo);
            TaskEncoder.addTask(taskList.get(taskList.size() - 1).toString());
            System.out.print(Skeleton.LINE_BREAK);
            System.out.println("added: " + taskList.get(taskList.size() - 1).getTask());
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
