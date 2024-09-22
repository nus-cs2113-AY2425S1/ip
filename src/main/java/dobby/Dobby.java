package dobby;

import dobby.parser.Parser;
import dobby.storage.Storage;
import dobby.tasklist.TaskList;
import dobby.ui.Ui;

import java.util.Scanner;

public class Dobby {

    public static void main(String[] args) {
        Storage storage = new Storage();
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        Parser parser = new Parser();

        storage.loadTasks(taskList.getTaskList());

        try (Scanner in = new Scanner(System.in)) {
            ui.printWelcomeMessage();
            String line;
            while (true) {
                line = in.nextLine().trim();
                if (line.equalsIgnoreCase("bye")){
                    break;
                }
                try {
                    parser.processCommand(line, taskList, ui, storage);
                } catch (Exception exception) {
                    ui.handleExceptions(exception);
                }
            }

            storage.saveTasks(taskList.getTaskList());
            ui.printGoodbyeMessage();
        }

    }


}
