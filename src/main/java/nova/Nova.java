package nova;

import java.util.Scanner;

public class Nova {

    public static void main(String[] args) {
        Ui.displayWelcomeMessage();
        TaskList taskManager = new TaskList();
        CommandHandler commandHandler = new CommandHandler(taskManager);
        Storage.createStorage();
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
