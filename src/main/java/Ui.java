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
    System.out.println("____________________________________________________________");
    System.out.println(" Hello! I'm Ruhi.");
    System.out.println(" What can I do for you?");
    System.out.println("____________________________________________________________");
  }

  public void showLoadingError() {
    System.out.println(" Oops! There was an error loading your tasks.");
  }

  public void showError(String errorMessage) {
    System.out.println(" Oopsy daisy! " + errorMessage);
  }
}
