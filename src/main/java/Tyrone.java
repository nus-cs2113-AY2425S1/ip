import java.util.Scanner;

import tyrone.constants.Constants;
import tyrone.exceptions.TyroneException;
import tyrone.storage.Storage;
import tyrone.parser.Parser;

public class Tyrone {

    /**
     * Main Logic for the entire Tyrone app
     * @param args
     */
        public static void main(String[] args) {

            //Load data from the txt file
            Constants.toDoList = Storage.loadTasks();

            try (Scanner in = new Scanner(System.in)) {
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