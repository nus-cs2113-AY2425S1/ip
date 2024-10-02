package commands;

import constants.Skeleton;
import tasks.Event;
import tasks.Task;
import storage.TaskEncoder;

import java.io.IOException;
import java.util.ArrayList;

public class EventCommand extends Command {
    private Event event;
    @Override
    public void execute(ArrayList<Task> taskList) {
        try {
            taskList.add(event);
            TaskEncoder.addTask(taskList.get(taskList.size() - 1).toString());
            System.out.print(Skeleton.LINE_BREAK);
            System.out.print("added: ");
            taskList.get(taskList.size() - 1).print();
            System.out.print(Skeleton.LINE_BREAK);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.print((Skeleton.LINE_BREAK));
            System.out.println("OH NO! You seem to have an invalid input!");
            System.out.println("Format: deadline <description> /from <from> /to <to>");
            System.out.print((Skeleton.LINE_BREAK));
        }  catch (IOException e) {
            System.out.print((Skeleton.LINE_BREAK));
            System.out.println("Seems like an error occurred");
            System.out.print((Skeleton.LINE_BREAK));
        }
    }
    public EventCommand(Event event) {
        this.event = event;
    }
}
