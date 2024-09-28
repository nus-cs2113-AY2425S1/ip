import java.util.Scanner;

import tyrone.constants.Constants;
import tyrone.exceptions.TyroneException;
import tyrone.storage.Storage;
import tyrone.parser.Parser;

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