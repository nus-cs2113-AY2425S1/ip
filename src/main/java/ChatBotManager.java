import java.util.Scanner;

public class ChatBotManager {

    private CommandHandler commandHandler;
    private Scanner input;

    public ChatBotManager(Task[] tasks, int taskCount) {
        this.commandHandler = new CommandHandler(tasks, taskCount);
        this.input = new Scanner(System.in);
    }

    public void start() {
        boolean isReadingInput = true;

        while (isReadingInput) {
            String line = input.nextLine().trim();
            String[] parts = line.split(" ");
            String commandStr = parts[0].toLowerCase();

            Command command = parseCommand(commandStr);
            if (command == Command.UNKNOWN) {
                System.out.println("Unknown command.");
            } else {
                commandHandler.handleCommand(command, line);
                if (command == Command.BYE) {
                    isReadingInput = false;
                }
            }
        }
    }

    private Command parseCommand(String commandStr) {
        try {
            return Command.valueOf(commandStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Command.UNKNOWN;
        }
    }
}