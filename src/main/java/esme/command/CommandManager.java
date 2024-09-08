package esme.command;

import esme.Ui;

public class CommandManager {
    public CommandManager() {}

    public boolean handleCommand(Ui ui, String line) {
        boolean toExit = false;
        String[] words = line.split(" ");
        switch (words[0]) {
        case "bye":
            new ExitCommand(ui).run();
            toExit = true;
            break;
        case "todo":
        case "deadline":
        case "event":
            new EntryCommand(ui,words[0],line).run();
            break;
        case "mark":
        case "unmark":
            new ActionCommand(ui,words).run();
            break;
        case "list":
            new PrintCommand(ui).run();
            break;
        default:
            new UnknownCommand(ui).run();
            break;
        }
        return toExit;
    }
}
