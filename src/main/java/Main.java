import bento.Bento;

/**
 * The Main class serves as the entry point for Bento.
 * It initializes and starts the chatbot.
 */
public class Main {
    /**
     * The main method is the entry point of the application.
     * It creates an instance of Bento and starts the chatbot.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Bento bento = new Bento();
        bento.run();
    }
}
