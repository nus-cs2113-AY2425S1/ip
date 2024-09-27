package doug.Main;

import doug.Commands.Command;
import doug.Parser;
import doug.UI;
import doug.TaskList;
import doug.Storage;

import java.io.FileNotFoundException;

/**
 * Represents the main chatbot Doug.
 */
public class Doug {

    private Storage storage;
    private TaskList tasks;
    private UI ui;

    public Doug(String filePath) {
        ui = new UI();
        storage = new Storage(filePath, ui);
        tasks = new TaskList();

        ui.printLogo();

        try {
            storage.loadTasks(tasks, ui, storage);
        } catch (FileNotFoundException e) {
            ui.sayNewUserWelcome();
        }
    }

    public void run() {
        boolean saidBye = false;

        while (!saidBye) {
            try {
                String command = ui.readCommand();
                Command user_command = Parser.extractCommandType(tasks, command);
                user_command.execute(tasks, ui, storage);
                saidBye = user_command.saidBye();
            } catch (DougException e) {
                System.out.println(e.getMessage());
            }
        }

        ui.sayGoodbye();
    }

    public static void main(String[] args) {
        new Doug("./data/tasks.txt").run();
    }
}
