import java.io.IOException;

/**
 * The ListBot class serves as the entry point of the application.
 * It initializes the task list and starts taking user input for managing tasks.
 */
public class ListBot {

    /**
     * The main method initializes the task list and begins user input parsing.
     *
     * @param args Command-line arguments (not used in this application).
     * @throws IOException If an I/O error occurs during task initialization or input handling.
     */
    public static void main(String[] args) throws IOException {
        TaskList.init(); // Initialize the task list.
        Parser.takeInput(); // Begin taking user input.
    }
}
