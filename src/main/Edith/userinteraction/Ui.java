package userinteraction;

import java.util.Scanner;
import static userinteraction.PrintShape.printHorizontalLine;

/**
 * Class which deals with direct interaction with the user.
 */
public class Ui {
    private final String MY_NAME = "Edith";

    /**
     * The chatbot introduces itself by name and prompts user input.
     */
    public void giveIntroduction() {
        System.out.println("Hello I am " + MY_NAME + ".");
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    /**
     * Takes in user input and passes that information to the
     * understandUser method of the Parser class. Repeatedly does
     * so until the user wants to exit.
     *
     * @param tasks
     * @param storage
     */
    public void interactWithUser(TaskList tasks, Storage storage) {
        Scanner sc = new Scanner(System.in);
        boolean isBye;
        do {
            String enteredString = sc.nextLine();
            printHorizontalLine();
            isBye = Parser.understandUser(tasks, enteredString, storage);
        } while (!isBye);
    }

    /**
     * Displays a simple goodbye message.
     */
    public void sayGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    /**
     * Informs the user that it was unsuccessful in opening the
     * existing file which contained the list of tasks and
     * that it is creating a new file to do the same.
     */
    public void showLoadingError() {
        System.out.println("Unable to load file. Creating a new file now!");
    }
}
