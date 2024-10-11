import java.io.IOException;

/**
 * The Main class serves as the entry point for the Duke application.
 * It initializes the Duke instance and starts the chatbot execution.
 */
public class Main {

    /**
     * The main method is the entry point of the Duke application.
     * It creates an instance of the Duke chatbot and executes it.
     *
     * @param args Command-line arguments (not used).
     * @throws DukeException If there is an error specific to Duke's execution.
     * @throws IOException If there is an input/output error.
     */
    public static void main(String[] args) throws DukeException, IOException {
        Duke LamboInstance = new Duke(); // Create an instance of the Duke chatbot
        LamboInstance.execute(); // Start the chatbot execution
    }
}
