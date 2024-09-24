package jeff;

import jeff.command.Command;
import jeff.exception.InvalidFormatException;
import jeff.helper.Parser;
import jeff.helper.Storage;
import jeff.helper.TaskList;
import jeff.helper.Ui;
import java.io.IOException;

public class Jeff {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Jeff(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);

        try{
            tasks = new TaskList(storage.loadTaskList());
        } catch (InvalidFormatException errorMessage) {
            ui.showLoadingError(String.valueOf(errorMessage));
            tasks = new TaskList();
        }
    }

    public void run() throws IOException {
        ui.showWelcome();
        boolean isExit = false;
        while(!isExit) {
            String fullCommand = ui.readCommand();
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
            ui.showNewLine();
            ui.showDivider();
            ui.showNewLine();
        }
    }

    public static void main(String[] args) throws IOException {
        new Jeff("data/taskList.txt").run();
    }
}
