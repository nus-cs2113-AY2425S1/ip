package commands;

import tasks.TaskList;
import ui.Skeleton;
import ui.Ui;

public class UnmarkCommand extends Command {
    private int index;
    @Override
    public void execute(TaskList taskList, Ui ui) {
        try {
            if (taskList.isEmpty()) {
                throw new NullPointerException();
            }
            taskList.get(index).setDone(false);
            System.out.print(Skeleton.LINE_BREAK);
            System.out.println("Aw you didn't get to finish. :(");
            taskList.get(index).print();
            System.out.print(Skeleton.LINE_BREAK);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException | NullPointerException e) {
            ui.printUnmarkError(e);
        }
    }
    public UnmarkCommand(int index) {
        this.index = index;
    }
}
