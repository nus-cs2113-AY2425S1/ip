package grok;

import grok.commands.Command;
import grok.parser.Parser;
import grok.storage.Storage;
import grok.tasks.Task;
import grok.tasks.TaskList;
import grok.ui.Ui;

import java.io.IOException;
import java.util.List;

public class Grok {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    public Grok(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);

        // Load tasks from the file
        List<Task> loadedTasks = storage.loadTasks();  // Load tasks from the file
        tasks = new TaskList(loadedTasks);  // Initialize TaskList with the loaded tasks
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();  // Show the divider line
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        try {
            new Grok("data/grok.txt").run();
        } catch (IOException e) {
            System.out.println("Error starting Grok: " + e.getMessage());
        }
    }
}
