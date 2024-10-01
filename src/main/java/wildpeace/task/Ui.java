package wildpeace.ui;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLine(String message) {
        System.out.println(message);
    }

    public void showError(String message) {
        System.err.println(message);
    }

    public void showGuide() {
        System.out.println("List your plans in the following format:");
        System.out.println("todo/ deadline/ event + task, e.g., todo homework");
        System.out.println("You may also mark/unmark tasks by: mark/unmark + task");
        System.out.println("Return to tutorial by entering Q");
    }

    public void close() {
        scanner.close();
    }
}
