package Glendon;

import java.io.FileNotFoundException;

public class Glendon {
    public static final String filePath = "./data/text.txt";
    private Storage storage;
    private TaskList tasks;
    private UI ui;

    /**
     * Construct the chatbot for operation
     *
     * @param filePath the designated file path for the saving of the task list after exiting from
     *                 chatbot
     */
    public Glendon(String filePath) {
        ui = new UI();
        storage = new Storage(filePath, ui);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the chatbot
     */
    public void run() {
        ui.showWelcome();
        while (true) {
            String fullCommand = ui.readCommand();
            Parser parser = new Parser(storage);
            parser.inputs(fullCommand, ui, tasks);
            ui.showLine();
            if (fullCommand.equals("bye")) {
                break;
            }
        }
    }
    
    public static void main(String[] args) {
        new Glendon(filePath).run();
    }
}