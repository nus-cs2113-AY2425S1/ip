package UserInteraction;

import TaskTypes.Task;
import java.util.ArrayList;
import java.util.Scanner;
import static UserInteraction.PrintShape.printHorizontalLine;

public class Ui {
    private final String MY_NAME = "Edith";

    public void giveIntroduction() {
        System.out.println("Hello I am " + MY_NAME + ".");
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    public void interactWithUser(TaskList tasks, Storage storage) {
        Scanner sc = new Scanner(System.in);
        boolean isBye;
        do {
            String enteredString = sc.nextLine();
            printHorizontalLine();
            isBye = Parser.understandUser(tasks, enteredString, storage);
        } while (!isBye);
    }

    public void sayGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    public void showLoadingError() {
        System.out.println("Unable to load file. Creating a new ArrayList");
    }
}
