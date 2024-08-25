import java.util.Scanner;

public class Mong {
    /**
     * Prints a horizontal line with width of 25 characters.
     */
    public static void printHorizontalLine() {
        for (int i = 0; i < 25; i += 1) {
            System.out.print("-");
        }
        System.out.println();
    }

    /**
     * Echoes the command sent by the user.
     * If the command "bye" is sent, the program exits.
     */
    public static void echoCommand() {
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();
        while (!command.equals("bye")) {
            printHorizontalLine();
            System.out.println("    " + command);
            printHorizontalLine();
            command = in.nextLine();
        }
    }

    public static void main(String[] args) {
        String logo = "\n" +
                "\n" +
                "___  ___                  _ \n" +
                "|  \\/  |                 | |\n" +
                "| .  . | ___  _ __   __ _| |\n" +
                "| |\\/| |/ _ \\| '_ \\ / _` | |\n" +
                "| |  | | (_) | | | | (_| |_|\n" +
                "\\_|  |_/\\___/|_| |_|\\__, (_)\n" +
                "                     __/ |  \n" +
                "                    |___/   \n" +
                "\n";
        printHorizontalLine();
        System.out.println("Hello, I am\n" + logo);
        System.out.println("What can I do for you?");
        printHorizontalLine();
        echoCommand();
        printHorizontalLine();
        System.out.println("Mong-mong... See you again next time!");
        printHorizontalLine();
    }
}
