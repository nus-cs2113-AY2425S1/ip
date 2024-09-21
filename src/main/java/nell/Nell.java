package nell;

import nell.common.Messages;
import nell.parser.Parser;
import nell.storage.Storage;
import nell.tasks.Deadline;
import nell.tasks.Event;
import nell.tasks.ToDo;

import java.util.Scanner;

public class Nell {
    private static TaskList tasks = new TaskList();
    private static Storage dataStorage = new Storage("./data/data.txt", tasks);
    private static Parser parser = new Parser(tasks);

    /**
     * Says bye to the user
     */
    private static void sayBye() {
        System.out.println(Messages.BYE_MESSAGE);
    }
    
    /**
     * Greet the user upon program startup
     */
    private static void greetUser() {
        System.out.println(Messages.HELLO_MESSAGE);
        System.out.println(Messages.PROMPT_MESSAGE);
    }

    /**
     * Get commands from user and execute commands received
     */
    private static void getCommands() {
        // Initialises scanner to take in user input
        Scanner input = new Scanner(System.in);

        boolean isGettingCommands = true;

        while (isGettingCommands) {
            // Get user command and respond accordingly
            String command = input.nextLine();
            if (command.equals("bye")) {
                sayBye();
                isGettingCommands = false;
            } else {
                parser.parseCommand(command);
            }
            dataStorage.saveToFile();
        }
    }

    public static void main(String[] args) {
        dataStorage.loadFromFile();
        greetUser();
        getCommands();
    }
}