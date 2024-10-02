package commands;

import constants.Skeleton;
import tasks.Task;
import storage.TaskEncoder;
import java.io.IOException;
import java.util.ArrayList;

public class UnmarkCommand extends Command {
    private int index;
    @Override
    public void execute(ArrayList<Task> taskList) {
        try {
            if (taskList.isEmpty()) {
                throw new NullPointerException();
            }
            taskList.get(index).setDone(false);
            TaskEncoder.unmarkTask(index);
            System.out.print(Skeleton.LINE_BREAK);
            System.out.println("Aw you didn't get to finish. :(");
            taskList.get(index).print();
            System.out.print(Skeleton.LINE_BREAK);
        } catch (NumberFormatException e) {
            System.out.print(Skeleton.LINE_BREAK);
            System.out.println("Woah, that wasn't a number?");
            System.out.println("format: unmark <number>");
            System.out.print(Skeleton.LINE_BREAK);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.print(Skeleton.LINE_BREAK);
            System.out.println("Uh oh. I think you didn't input a number :/ ");
            System.out.println("format: unmark <number>");
            System.out.print(Skeleton.LINE_BREAK);
        } catch (NullPointerException e) {
            System.out.print(Skeleton.LINE_BREAK);
            System.out.println("Uh oh. I think you gave too big a number :/ ");
            System.out.println("format: unmark <number smaller than number of tasks>");
            System.out.print(Skeleton.LINE_BREAK);
        } catch (IOException e) {
            System.out.print(Skeleton.LINE_BREAK);
            System.out.println("Hmm, there seems to be a File error?");
            System.out.print(Skeleton.LINE_BREAK);
        }
    }
    public UnmarkCommand(int index) {
        this.index = index;
    }
}
