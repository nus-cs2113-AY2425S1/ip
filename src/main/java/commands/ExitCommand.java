package commands;

import constants.Skeleton;
import tasks.Task;
import exceptions.IllegalCommandException;

import java.util.ArrayList;

public class ExitCommand extends Command {
    @Override
    public void execute(ArrayList<Task> taskList) throws IllegalCommandException {
        System.out.print(Skeleton.LINE_BREAK);
        System.out.println("Bye. See you next time!");
        System.out.print(Skeleton.LINE_BREAK);
    }
}
