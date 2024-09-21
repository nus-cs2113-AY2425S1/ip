package niwa.command;

import niwa.Niwa;
import niwa.data.Storage;
import niwa.data.task.TaskList;

import java.io.IOException;

public class ExecutedCommand {
    public static void saveTasks() {
        Storage storage = new Storage(Niwa.getOutputFilePath());
        try {
            storage.writeTaskList(TaskList.getInstance().getTaskList());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
