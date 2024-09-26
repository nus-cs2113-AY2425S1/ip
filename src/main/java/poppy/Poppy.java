package poppy;


import java.io.FileNotFoundException;
import java.io.IOException;

public class Poppy {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Poppy(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            storage.createFileIfNotExists();
            taskList = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showMessage("File not found");
            taskList = new TaskList();
        } catch (IOException e) {
            ui.showMessage("Error updating file");
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            String input = ui.getUserInput();
            try {
                Parser.processCommand(input, taskList);
                storage.save(taskList.getTasks());
            } catch (IOException e) {
                ui.showMessage(e.getMessage());
            }
            if (input.equals("Bye")) {
                isExit = true;
            }
        }

        ui.showGoodbye();
        ui.close();
    }

    public static void main(String[] args) {
        new Poppy("./data/Poppy.txt").run();
    }
}


