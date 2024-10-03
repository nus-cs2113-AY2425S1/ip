import java.util.Scanner;

import tyrone.constants.Constants;
import tyrone.exceptions.TyroneException;
import tyrone.storage.Storage;
import tyrone.parser.Parser;

    /**
     * Main Logic for the entire Tyrone app
     * The process of the main logic goes as follows,
     * The system loads the data saved in data/Tasks.txt
     * Then it starts to accept inputs in the command line to be passed to the Parser package
     * Only when it reads a "bye" does it leave the main loop and quit the Java CLI Application
     * @param args
     */

public class Tyrone {
        public static void main(String[] args) {
        Constants.toDoList = Storage.loadTasks();
        try (Scanner in = new Scanner(System.in)) {
            // Constants acts as my ui package
            Constants.intro();
            String input = in.nextLine();
            while (!input.equals("bye")) {
                try {
                    Parser.getUserInput(input); 
                } catch (TyroneException e) {
                    // General exception handling for TyroneException
                    System.out.println("An error occurred: " + e.getMessage());
                }
                input = in.nextLine();
            }
        }
        Constants.goodbye();
    }
}