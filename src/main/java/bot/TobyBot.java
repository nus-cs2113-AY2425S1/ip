package bot;

import bot.command.Command;
import task.TaskList;

import java.io.IOException;

/**
 * Represents the main class for TobyBot, a task management bot.
 */
public class TobyBot {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructor for TobyBot.
     *
     * @param filePath The file path to load and save tasks.
     */
    public TobyBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the TobyBot, allowing the user to interact with it.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (TobyBotException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Main method to start TobyBot.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new TobyBot("data/TobyBot.txt").run();
    }
}