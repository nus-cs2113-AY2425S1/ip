import command.Command;
import datahandling.Storage;
import task.TaskList;
import ui.UserInteraction;
import parser.Parser;
import exception.InvalidTaskNumberException;
import exception.UnknownCommandException;
import java.io.IOException;

/**
 * Main class for Jer Chat Bot,
 * Initializes bot, handling user commands and managing tasks.
 */
public class JerChatBot {
    private static final String FILE_PATH = "./src/main/java/data/tasks.txt";
    
    private final Storage storage;
    private final UserInteraction ui;
    private TaskList tasks;

    /**
     * Constructs and initializes Jer Chat Bot with specified file path for task storage.
     * @param filePath path of file to use for storage
     */
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

    /**
     * Runs bot, reading user commands in a loop until exit message is given.
     */
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

    /**
     * Main method to start bot.
     * @param args CLI arguments
     */
    public static void main(String[] args) {
        new JerChatBot(FILE_PATH).runProgram();
    }
}
