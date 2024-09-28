import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        printLine();
        System.out.println(" Hello! I'm KaiWen");
        System.out.println(" What can I do for you?");
        printLine();
    }

    public void showGoodbye() {

        System.out.println(" Bye. Hope to see you again soon!");

    }

    public void showError(String message) {

        System.out.println(" OOPS!!! " + message);

    }

    public void showLine() {
        printLine();
    }

    public void showLoadingError() {
        showError("Error loading tasks from file.");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    private void printLine() {
        System.out.println("____________________________________________________________");
    }
}
