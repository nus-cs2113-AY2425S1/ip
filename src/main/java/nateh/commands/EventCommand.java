package nateh.commands;

import nateh.Skeleton;
import nateh.classes.Event;
import nateh.classes.Task;
import nateh.storage.TaskEncoder;

import java.io.IOException;
import java.util.ArrayList;

public class EventCommand extends Command {
    private Event event;
    @Override
    public void execute(ArrayList<Task> taskList) {
        try {
            taskList.add(event);
            TaskEncoder.addTask(taskList.getLast().toString());
            System.out.print(Skeleton.LINE_BREAK);
            System.out.print("added: ");
            taskList.getLast().print();
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
