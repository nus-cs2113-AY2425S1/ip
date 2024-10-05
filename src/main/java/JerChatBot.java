import command.Command;
import datahandling.Storage;
import task.TaskList;
import ui.UserInteraction;
import parser.Parser;
import exception.InvalidTaskNumberException;
import exception.UnknownCommandException;
import java.io.IOException;

public class JerChatBot {
    private static final String FILE_PATH = "./src/main/java/data/tasks.txt";
    
    private final Storage storage;
    private final UserInteraction ui;
    private TaskList tasks;

    public JerChatBot(String filePath) {
        ui = new UserInteraction();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTaskFromFile());
        } catch (IOException e) {
            ui.showLoadingError();
            e.printStackTrace();
            tasks = new TaskList();
        }
    }

    public void runProgram() {
        ui.showWelcomeMessage();
        boolean isExit = false;

        while (!isExit) {
            String fullCommand = ui.readCommand();
            try {
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (UnknownCommandException | InvalidTaskNumberException e) {
                ui.showMessage(e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                ui.printDividerLine();
            }
        }
    }

    public static void main(String[] args) {
        new JerChatBot(FILE_PATH).runProgram();
    }
}
