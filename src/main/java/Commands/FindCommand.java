package Commands;

import taskmanager.Storage;
import tasks.Task;

import java.util.ArrayList;

public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword.substring(4).trim();
    }

    @Override
    public void execute(Storage storage) {
        System.out.println("Here are the matching tasks in your list:");

        int index;
        int count = 0;
        for (index = 0; index < storage.getTaskList().size(); index++) {
            Task task = storage.getTaskList().get(index);
            if (task.getContents().toLowerCase().contains(keyword)) {
                storage.storagePrintTask(index + 1);
                count++;
            }
        }

        if (count == 0){
            System.out.println("No matching tasks found with keyword " + keyword);
        }
    }
}
