package bron.ui;

import java.util.Scanner;

public class TextUI {
    private static final String DIVIDER = "____________________________________________________________";
    private static final String LOGO = """
              ____    ____      ____    _     _
             |  _ \\  |   _\\   /  __  \\ | \\   | |
             | |_) | | |_) |  | |  | | |  \\  | |\s
             |  _ <  | ___/   | |  | | | |\\\\ | |
             | |_) | | | \\ \\  | |__| | | | \\\\| |
             |____/  |_|  \\_\\  \\____/  |_|  \\__|
            """;
    private final Scanner scanner;

    public TextUI() {
        scanner = new Scanner(System.in);
    }

    public static void displayIntro() {
        System.out.println(LOGO + "Hello! I'm Bron\n" + "What can I do for you?\n");
    }

    public String readCommand() {
        System.out.print("Enter command: ");
        return scanner.nextLine();
    }

    public static void showLine() {
        System.out.println(DIVIDER);
    }

    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showLoadingError() {
        showError("Failed to load task data.");
    }

    public void close() {
        scanner.close();
    }

}
