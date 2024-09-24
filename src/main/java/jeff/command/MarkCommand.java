package jeff.command;

import jeff.helper.Storage;
import jeff.helper.TaskList;
import jeff.helper.Ui;
import jeff.task.Task;

import java.io.IOException;

public class MarkCommand extends Command {
    public MarkCommand(String firstWord, String line) {
        super(firstWord, line);
    }

    //Marks task as complete/uncomplete, catches any errors thrown
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        try {
            int taskNumber = super.getTaskNumber(this.line, tasks.getCount());
            Task task = tasks.getTask(taskNumber - 1);
            if(firstWord.equals("mark")) {
                task.setIsDone(true);
                System.out.print("ogei marked task dOnE");
            }
            else{
                task.setIsDone(false);
                System.out.print("womp womp task not finished :(");
            }

            storage.writeFileTask(tasks);
            System.out.print(System.lineSeparator() + task);
        } catch(IllegalArgumentException errorMessage) {
            System.out.print("can u plsplspls give me a number!!!");
        } catch(IndexOutOfBoundsException errorMessage){
            System.out.print("u tryna mark a task number that isn't in the list...");
        }
    }
}
