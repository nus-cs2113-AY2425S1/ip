import esme.storage.Storage;
import esme.ui.Ui;
import esme.command.CommandManager;

import java.util.Scanner;

public class ChatBot {
    private final Scanner inputScanner;
    private final Ui ui;
    private final CommandManager commandManager;
    private Storage storage;

    public ChatBot(String filePath) {
        this.inputScanner = new Scanner(System.in);
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.commandManager = new CommandManager(this.ui,this.storage);
    }

    public void bootUp() {
        if (!commandManager.hasLoadSuccessful()) {
            ui.printNoFileMessage();
            System.exit(0);
        }
        ui.greet();
    }

    public void start() {
        bootUp();
        String line;
        boolean toExit = false;

        while (!toExit) {
            line = inputScanner.nextLine();
            toExit = commandManager.handleCommand(line);
        }
        inputScanner.close();
    }

    public static void main(String[] args) {
        ChatBot esme = new ChatBot("tasklist.txt");
        esme.start();
    }
}
