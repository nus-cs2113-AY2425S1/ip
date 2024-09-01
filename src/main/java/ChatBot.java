import esme.Ui;

import java.util.Scanner;

public class ChatBot {
    private Scanner in;
    private Ui ui;

    public ChatBot() {
        this.in = new Scanner(System.in);
        this.ui = new Ui();
    }

    public void start() {
        ui.greet();
        String line;

        while (true) {
            line = in.nextLine();
            String[] words = line.split(" ");
            if (words.length == 0) {
                ui.promptEmptyInput();
            }
            switch (words[0]) {
            case "bye":
                ui.farewell();
                in.close();
                System.exit(0);
                break;
            case "todo":
            case "deadline":
            case "event":
                ui.addTaskToList(words[0],line);
                break;
            case "mark":
            case "unmark":
                ui.handleTaskStatus(words);
                break;
            case "list":
                ui.printTaskList();
                break;
            default:
                ui.handleUnknownCommand();
                break;
            }
        }
    }

    public static void main(String[] args) {
        ChatBot esme = new ChatBot();
        esme.start();
    }
}
