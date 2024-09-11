package aether.ui;

/**
 * Display class to handle all user interface outputs.
 */
public class Display {
    public static void showStartScreen() {
        String logo = "     ___   ______  _______  _    _  ______  _____\n"
                + "    /   | |  ____||__   __|| |  | ||  ____||  __ \\\n"
                + "   / /| | | |__      | |   | |__| || |__   | |__) |\n"
                + "  / /_| | |  __|     | |   |  __  ||  __|  |  _  /\n"
                + " /  __  | | |____    | |   | |  | || |____ | | \\ \\\n"
                + "/_/   |_| |______|   |_|   |_|  |_||______||_|  \\_\\\n";
        String startScreen = "Aether:\n" + "Hello! I'm Aether, your friendly assistant.\n"
                + "How can I help you today?\n";

        System.out.println(logo);
        printSeparator();
        System.out.println(startScreen);
        printSeparator();
    }

    public static void showEndScreen() {
        String endScreen = "Aether:\n" + "Goodbye! Hope to see you again soon!";
        System.out.println(endScreen);
    }

    public static void printSeparator() {
        System.out.println("____________________________________________________");
    }

    public static void response(String message) {
        System.out.println("Aether:\n" + message);
    }
}
