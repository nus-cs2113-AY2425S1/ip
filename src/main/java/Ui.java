/**
 * Handles the user interface for Ruhi, managing input and output interactions with the user of the
 * chatbot.
 */

import java.util.Scanner;

public class Ui {

  private Scanner scanner;

  public Ui() {
    scanner = new Scanner(System.in);
  }

  public String readCommand() {
    return scanner.nextLine();
  }

  public void showLine() {
    System.out.println("____________________________________________________________");
  }

  public void showWelcome() {
    /**
     * Displays a welcome message to the user.
     */
    System.out.println("____________________________________________________________");
    System.out.println(" Hello! I'm Ruhi.");
    System.out.println(" What can I do for you?");
    System.out.println("____________________________________________________________");
  }

  public void showLoadingError() {
    /**
     * Displays a loading error message when tasks can't be loaded.
     */
    System.out.println(" Oops! There was an error loading your tasks.");
  }

  public void showError(String errorMessage) {
    /**
     * Displays an error message to the user.
     */
    System.out.println(" Oopsy daisy! " + errorMessage);
  }
}
