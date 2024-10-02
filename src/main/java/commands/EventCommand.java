package commands;

import java.util.ArrayList;

import tasks.Event;
import tasks.Task;
import ui.Skeleton;
import ui.Ui;


public class EventCommand extends Command {
    private Event event;
    @Override
    public void execute(ArrayList<Task> taskList, Ui ui) {
        try {
            taskList.add(event);
            System.out.print(Skeleton.LINE_BREAK);
            System.out.print("added: ");
            taskList.get(taskList.size() - 1).print();
            System.out.print(Skeleton.LINE_BREAK);
        } catch (StringIndexOutOfBoundsException e) {
            ui.invalidTask(event);
        }
    }
    public EventCommand(Event event) {
        this.event = event;
    }
}
