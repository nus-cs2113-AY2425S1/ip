import esme.Ui;
import esme.command.CommandManager;

import java.util.Scanner;

public class ChatBot {
    private final Scanner inputScanner;
    private final Ui ui;
    private final CommandManager commandManager;

    public ChatBot() {
        this.inputScanner = new Scanner(System.in);
        this.ui = new Ui();
        this.commandManager = new CommandManager();
    }

    public void start() {
        ui.greet();
        String line;
        boolean toExit = false;

        while (!toExit) {
            line = inputScanner.nextLine();
            if (line.isEmpty()) {
                ui.promptEmptyInput();
                continue;
            }
            toExit = commandManager.handleCommand(ui,line);
        }
        inputScanner.close();
    }

    public static void main(String[] args) {
        ChatBot esme = new ChatBot();
        esme.start();
    }
}
