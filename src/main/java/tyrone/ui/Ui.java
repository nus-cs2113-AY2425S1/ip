package tyrone.ui;

import java.util.Scanner;
import tyrone.constants.Constants;

public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLine() {
        System.out.println(Constants.LINE);
    }

    public void showWelcome() {
        System.out.println(Constants.LINE);
        System.out.println("    Hello from\n" + Constants.logo + "\n");
        System.out.println("    What can I do for you cuh?\n");
        System.out.println(Constants.LINE);
    }

    public void showGoodbye() {
        System.out.println(Constants.LINE);
        System.out.println("    see you brother");
        System.out.println(Constants.LINE);
    }

    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    public void showLoadingError() {
        System.out.println(Constants.LINE);
        System.out.println("    Tasks.txt file not found.");
        System.out.println(Constants.LINE);
    }
}
