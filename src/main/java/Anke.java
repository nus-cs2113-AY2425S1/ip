import java.util.Scanner;

/**
 * Anke : a Chat Bot
 *
 */

public class Anke {
    private Storage storage;
    private Ui ui;
    private Parser parser;
    public static boolean isExit = false;

    /**
     * constructor of Anke class
     * initialize ui and storage
     * load data from file path
     *
     * @param filePath the file path where previous data is stored
     * @throws AnkeException If file not found
     */
    public Anke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath, ui);
        parser = new Parser(storage.getTasklist());
        try {
            Storage.load();
        } catch (AnkeException e) {
            ui.showLoadingError();
        }
    }

    /**
     * Chat Bot handling user input
     *
     * @throws AnkeException If parser cannot handle command
     *
     */
    public void run() {
        ui.showWelcome();
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Parser.handle(fullCommand);
            } catch (AnkeException e) {
                ui.showError(e.getMessage());
            }
        }
        Ui.printBye();
    }

    /**
     * main function
     *
     */
    public static void main(String[] args) {
        Anke anke = new Anke("./Anke.txt");
        anke.run();
    }
}
