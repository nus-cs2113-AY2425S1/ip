package doug.Main;

import doug.Parser;
import doug.UI;
import doug.TaskList;
import doug.Storage;

import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * Represents the main chatbot Doug.
 */
public class Doug {

    public static void main(String[] args) {
        boolean saidBye = false;
        TaskList tasks = new TaskList();
        UI.printLogo();

        try {
            Storage.loadTasks(tasks);
        } catch (FileNotFoundException e) {
            UI.sayNewUserWelcome();
        }

        Scanner input = new Scanner(System.in);

        while (!saidBye) {
            try {
                String command = input.nextLine().trim();
                saidBye = Parser.extractCommandType(tasks, command);
            } catch (DougException e) {
                System.out.println(e.getMessage());
            }
        }

        UI.sayGoodbye();
    }
}
