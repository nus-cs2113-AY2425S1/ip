package nova;

import java.util.Scanner;

public class Nova {

    public static void main(String[] args) {
        MessageDisplay.displayWelcomeMessage();
        TaskManager taskManager = new TaskManager();
        InputValidator inputValidator = new InputValidator();
        CommandHandler commandHandler = new CommandHandler(taskManager, inputValidator);
        Scanner sc = new Scanner(System.in);

        while (true) {
            String[] userInput = getUserInput(sc);
            if (commandHandler.handleInput(userInput)) {
                return;
            }
        }
    }

    private static String[] getUserInput(Scanner sc) {
        if (!sc.hasNextLine()) {
            return new String[]{"bye"};
        }
        String info = sc.nextLine();
        return info.split(" ", 2);
    }
}
