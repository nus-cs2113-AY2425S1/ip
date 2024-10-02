package commands;

import tasks.TaskList;
import ui.Skeleton;
import ui.Ui;


public class MarkCommand extends Command {
    private int index;
    public MarkCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList taskList, Ui ui) {
        try {
            if (taskList.isEmpty()) {
                throw new NullPointerException();
            }
            taskList.get(index).setDone(true);
            System.out.print(Skeleton.LINE_BREAK);
            System.out.println("Wow! Great job! :)");
            taskList.get(index).print();
            System.out.print(Skeleton.LINE_BREAK);
        } catch (NumberFormatException | NullPointerException | ArrayIndexOutOfBoundsException e) {
            ui.printMarkError(e);
        }
    }
}
