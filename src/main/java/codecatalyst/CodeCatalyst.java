package codecatalyst;

import codecatalyst.command.Command;

import java.io.IOException;


public class CodeCatalyst {
    private static final String FILE_PATH = "data/CodeCatalystData.txt";
    private Storage storage;
    private TaskList tasklist;
    private Ui ui;

    public CodeCatalyst(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);

        try {
            tasklist = new TaskList(storage.loadTasksFromFile());
        } catch (IOException e){
            ui.printLoadingError();
            tasklist = new TaskList();
        }
    }

    public void runCodeCatalyst() {
        ui.printGreeting();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printDivider();
                Command command = Parser.parse(fullCommand);
                command.execute(tasklist, ui, storage);
                isExit = command.isExit();
            } catch (Exception e) {
                ui.printError(e.getMessage());
            } finally {
                ui.printDivider();
            }
        }
    }

    public static void main(String[] args) {
        new CodeCatalyst(FILE_PATH).runCodeCatalyst();
    }
}
