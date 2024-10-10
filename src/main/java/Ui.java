import java.util.Scanner;

public class Ui {
  public void showWelcome() {
    System.out.println("____________________________________________________________");
    System.out.println(" Hello! I'm Ruhi.");
    System.out.println(" What can I do for you?");
    System.out.println("____________________________________________________________");
  }

  public void showLine() {
    System.out.println("____________________________________________________________");
  }

  public void showLoadingError() {
    System.out.println("Error loading tasks.");
  }

  public String readCommand() {
    Scanner scanner = new Scanner(System.in);
    return scanner.nextLine();
  }

  public void showError(String message) {
    System.out.println(message);
  }
}
