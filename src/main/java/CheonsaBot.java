import parser.Parser;
import storage.Storage;
import ui.UI;

public class CheonsaBot {
    /**
     * The entry point of the application. Starts bot and listens for user input.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Storage.createFileIfNotExists(); 
        Storage.loadTasksFromFile();    
        UI.greet();

        boolean running = true;
        while (running) {
            String input = UI.getUserInput();
            running = Parser.parseInput(input);
        }
    }
}
