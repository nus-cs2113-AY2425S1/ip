import java.util.Arrays;
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
     * Prints out an indexed list of commands given to Mong starting from 1.
     */
    public static void printIndexedList(String[] list) {
        for (int i = 0; i < list.length; i++) {
            System.out.println(Integer.toString(i + 1) + "." + list[i]);
        }
    }

    /**
     * Adds the command sent by the user into a list.
     * If the command "list" is sent, the list of previous commands will be printed.
     * If the command "bye" is sent, the program will exit.
     */
    public static void addItems() {
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();
        String[] list = new String[100];
        int listIndex = 0;
        while (!command.equals("bye")) {
            printHorizontalLine();
            if (command.equals("list")) {
                // print items in an indexed list
                printIndexedList(Arrays.copyOf(list, listIndex));
            } else {
                System.out.println("Mong-ed! This item has been added: " + command);
                list[listIndex] = command;
                listIndex++;
            }
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
        addItems();
        printHorizontalLine();
        System.out.println("Mong-mong... See you again next time!");
        printHorizontalLine();
    }
}
