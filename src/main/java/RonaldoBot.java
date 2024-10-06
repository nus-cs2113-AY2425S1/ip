import cristiano.exceptions.RonaldoException;
import cristiano.ui.Ronaldo;
import cristiano.storage.Storage;
import cristiano.commands.Command;
import cristiano.commands.Parser;

import java.util.Scanner;

/**
 * The main class of the Ronaldo chatbot.
 * This chatbot develops a personality of Cristiano Ronaldo,
 * a world renowned footballer and the most followed celebrity in the world.
 */
public class RonaldoBot {
    private final Ronaldo ronaldo;
    private final Storage storage;
    private final Scanner input;

    /**
     * Constructs a Main object to initialize the Ronaldo chatbot
     *
     * @param filepath The path to the data file used for storing and retrieving goals.
     */
    public RonaldoBot(String filepath) {
        this.storage = new Storage(filepath);
        this.ronaldo = new Ronaldo(storage);
        this.input = new Scanner(System.in);
    }

    /**
     * Runs the main loop of the Ronaldo chatbot.
     * The chatbot greets the user, then continuously waits for user input,
     * before processing their commands.
     * The loop handles any exceptions that arise during execution.
     * The loop ends when the user issues an exit command.
     */
    public void run() {
        ronaldo.greet();
        String command;
        boolean isEnd = false;
        while (!isEnd) {
            try {
                command = input.nextLine();
                Command c = Parser.parse(command);
                c.execute(ronaldo);
                isEnd = c.hasEnd();
            } catch (RonaldoException e) {
                ronaldo.showError(e.getMessage());
            } finally {
                ronaldo.showIndentation();
            }
        }
        input.close();
    }

    /**
     * The main entry point of the application.
     * Creates a Main object with the specified file path for storage and runs the chatbot.
     *
     * @param args Command-line arguments;
     */
    public static void main(String[] args) {
        new RonaldoBot("data/cr7.txt").run();
    }

}
