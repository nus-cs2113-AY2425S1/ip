import java.util.Scanner;

public class Aether {
    private boolean isExit = false;

    public static void main(String[] args) {
        Aether aether = new Aether();
        aether.run();
    }

    public void run() {
        Display.showStartScreen();
        Scanner scanner = new Scanner(System.in);

        while (!isExit) {
            System.out.println("You:");
            String command = scanner.nextLine();
            Display.printSeparator();
            handleCommand(command);
        }
    }

    private void handleCommand(String command) {
        switch (command.toLowerCase()) {
        case "exit":
            isExit = true;
            Display.showEndScreen();
            break;
        default:
            Display.echoCommand(command);
            break;
        }
        Display.printSeparator();
    }
}

class Display {
    public static void showStartScreen() {
        String logo = "     ___   ______  _______  _    _  ______  _____   \n"
                + "    /   | |  ____||__   __|| |  | ||  ____||  __ \\  \n"
                + "   / /| | | |__      | |   | |__| || |__   | |__) | \n"
                + "  / /_| | |  __|     | |   |  __  ||  __|  |  _  /  \n"
                + " /  __  | | |____    | |   | |  | || |____ | | \\ \\  \n"
                + "/_/   |_| |______|   |_|   |_|  |_||______||_|  \\_\\ \n";
        String startScreen = "Aether:\n"
                + "Hello! I'm Aether, your friendly assistant.\n"
                + "How can I help you today?\n";

        System.out.println(logo);
        printSeparator();
        System.out.println(startScreen);
        printSeparator();
    }

    public static void showEndScreen() {
        String endScreen = "Aether:\n"
                + "Goodbye! Hope to see you again soon!";
        System.out.println(endScreen);
    }

    public static void printSeparator() {
        System.out.println("____________________________________________________");
    }

    public static void echoCommand(String message) {
        System.out.println("Aether:\n" + message);
    }
}