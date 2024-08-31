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
     * If the item has been marked as 'completed', the [ ] box will be checked with an '[X]'.
     */
    public static void printIndexedList(Task[] list) {
        for (int i = 0; i < list.length; i++) {
            if (list[i].isCompleted()) {
                System.out.println(Integer.toString(i + 1) + ". " + "[X] " + list[i].getText());
            } else {
                System.out.println(Integer.toString(i + 1) + ". " + "[ ] " + list[i].getText());
            }
        }
    }

    /**
     * Adds the command sent by the user into a list.
     * If the command "list" is sent, the list of previous commands will be printed.
     * If the command's first word is "mark", it will set the isCompleted variable of the Task to true.
     * If the command's first word is "unmark", it will set the isCompleted variable of the Task to false.
     * If the command "bye" is sent, the program will exit.
     */
    public static void addItems() {
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();
        Task[] list = new Task[100];
        int itemIndex = 0;
        while (!command.equals("bye")) {
            printHorizontalLine();
            if (command.equals("list")) {
                // print items in an indexed list
                printIndexedList(Arrays.copyOf(list, Task.currentIndex));
            } else if (command.split(" ")[0].equals("mark")) {
                // the itemIndex is -1 than the input from the user
                itemIndex = Integer.parseInt(command.split(" ")[1]) - 1;
                list[itemIndex].setCompleted(true);
            } else if (command.split(" ")[0].equals("unmark")) {
                // the itemIndex is -1 than the input from the user
                itemIndex = Integer.parseInt(command.split(" ")[1]) - 1;
                list[itemIndex].setCompleted(false);
            } else {
                // add an item to the list
                list[Task.currentIndex] = new Task(command);
                System.out.println("Mong-ed! This item has been added: " + command);
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
