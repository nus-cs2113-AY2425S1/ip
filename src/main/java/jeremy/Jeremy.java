package jeremy;

import jeremy.command.Command;

import jeremy.exception.JeremyException;
import java.io.FileNotFoundException;

import jeremy.util.Parser;
import jeremy.util.Storage;
import jeremy.util.Ui;
import jeremy.util.TaskList;

public class Jeremy {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Jeremy() {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.println("Storage file couldn't be created");
        }
    }

    public void run() {
        ui.greeting();
        ui.logo();
        boolean isExit = false;

        while (!isExit) {
            try {
                String userInput = ui.readCommand();
                Command command = Parser.parse(userInput);
                command.execute(this.tasks, this.ui, this.storage);
                isExit = command.isExit();
            } catch (JeremyException e) {
                ui.lineBreak();
                ui.println(e.getMessage());
                ui.lineBreak();
            }
        }

        storage.save(tasks);
        ui.bye();
    }

    public static void main(String[] args) {
        new Jeremy().run();
    }
}
