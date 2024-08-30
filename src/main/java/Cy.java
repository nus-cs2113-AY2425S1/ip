import java.util.Scanner;

public class Cy {
    public static void printLine() {
        System.out.println("______________________________________");
    }

    public static void markItem(String[] splitInputs, Task[] items) {
        int index = Integer.parseInt(splitInputs[1]) - 1;
        items[index].isDone = true;
        printLine();
        System.out.println("Nice! I've marked this task as done :");
        System.out.println("[X] " + items[index].description);
        printLine();
    }

    public static void unmarkItem(String[] splitInputs, Task[] items) {
        int index = Integer.parseInt(splitInputs[1]) - 1;
        items[index].isDone = false;
        printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("[ ] " + items[index].description);
        printLine();
    }

    public static void printList(Task[] items) {
        printLine();
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                System.out.println((i + 1) + ". [" + items[i].getStatusIcon() + "] " + items[i].description);
            } else {
                break;
            }
        }
        printLine();
    }

    public static void addItem(Task[] items, int count, String input) {
        items[count] = new Task(input);
        printLine();
        System.out.println("added: " + input);
        printLine();
    }

    public static void main(String[] args) {
        System.out.println("Hello, I'm Cy");
        System.out.println("What can I do for you?");

        Task[] items = new Task[100];
        int count = 0;

        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();

        while (!input.equalsIgnoreCase("bye")) {

            String[] splitInputs = input.split(" ");

            if (input.equalsIgnoreCase("list")) {
                printList(items);
            } else if (splitInputs[0].equalsIgnoreCase("mark")) {
                markItem(splitInputs, items);
            } else if (splitInputs[0].equalsIgnoreCase("unmark")) {
                unmarkItem(splitInputs, items);
            } else {
                addItem(items, count, input);
                count++;
            }
            input = scan.nextLine();
        }

        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
}