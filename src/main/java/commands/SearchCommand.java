package commands;

import tasks.TaskList;
import ui.Ui;

public class SearchCommand extends Command {
    private String flag;
    private String searchTerm;

    public SearchCommand(String flag, String searchTerm) {
        this.flag = flag;
        this.searchTerm = searchTerm;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        if (flag.equals("/c")) {
            for (int i = 0; i < taskList.size(); i++) {
                if (taskList.get(i).getTask().contains(searchTerm)) {
                    System.out.print(i + 1 + ". ");
                    taskList.get(i).print();
                }
            }
        }
    }


}
