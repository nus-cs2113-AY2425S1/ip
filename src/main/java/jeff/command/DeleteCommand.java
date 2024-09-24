package jeff.command;

import jeff.helper.Storage;
import jeff.helper.TaskList;
import jeff.helper.Ui;
import jeff.task.Task;

import java.io.IOException;

public class DeleteCommand extends Command {

    public DeleteCommand(String firstWord, String line) {
        super(firstWord, line);
    }

    //Deletes the specified task, catches any error thrown
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        try {
            int taskNumber = super.getTaskNumber(line, tasks.getCount());
            Task task = tasks.getTask(taskNumber - 1);
            tasks.removeTask(taskNumber - 1);
            System.out.print("i have reeeemoved the taskkk: " +
                    System.lineSeparator() + task);
            storage.writeFileTask(tasks);

        } catch(IllegalArgumentException errorMessage) {
            System.out.print("eh how to delete a non-number task...");
        } catch(IndexOutOfBoundsException errorMessage){
            System.out.print("how to delete a non-existent task...");
        }
        System.out.print(System.lineSeparator() +
                "YAYYYY!!! Only " + tasks.getCount() + " task(s) left in ur list!");
    }
}
