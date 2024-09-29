package appal;

/**
 * Main class provides the main function to initialise and run Appal.
 */
public class Main {
    /**
     * Creates an instance of the chatbot Appal then runs it.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Appal appal = new Appal();
        appal.runAppal();
    }
}