package nus.edu.rizzler.ui;

/**
 * Handles interactions with the user, including displaying messages and handling errors.
 */
public class UserInterface {
    private Face face = new Face();
    private final String GREET_MESSAGE = "Hi! I'm Rizzler.";
    private final String HELLO_MESSAGE = "What can I do to make your day pop? " + face.getPartyFace();
    private final String GOODBYE_MESSAGE = "Peace out! <3";
    private final String LOGO = """
                 _____                          _\s
                |     \\                        | |\s
                | |_)  |      ______   ______  | |   ____    _  __\s
                |     /  (_) |__   /  |__   /  | | /      \\ | |/  \\\s
                | |\\ \\   | |   /  /     /  /   | | |  ____/ |  _/\\_\\\s
                | | \\ \\  | |  /  / __  /  / __ | | | |____  | | \s
                |_|  \\_\\ |_| /______/ /______/ |_|  \\_____| |_|\s
                """;

    /**
     * Displays the greeting message and the logo to the user.
     */
    public void sayHello() {
        System.out.println(GREET_MESSAGE);
        System.out.println(LOGO);
        System.out.println(HELLO_MESSAGE);
    }

    /**
     * Displays the goodbye message to the user.
     */
    public void sayGoodbye() {
        System.out.println(GOODBYE_MESSAGE);
    }

    /**
     * Prints a divider line to the console for visual separation.
     */
    public void drawLine() {
        System.out.println("------------------------------------------------");
    }

    /**
     * Displays an error message to the user, along with the exception message.
     *
     * @param e The exception whose message will be displayed.
     */
    public void displayError(Exception e) {
        System.out.println("**** ERROR: " + e.getMessage() + " ****");
    }

    /**
     * Displays a specified message to the user.
     *
     * @param message The message to display.
     */
    public void displayMessage(String message) {
        System.out.println(message);
    }
}
